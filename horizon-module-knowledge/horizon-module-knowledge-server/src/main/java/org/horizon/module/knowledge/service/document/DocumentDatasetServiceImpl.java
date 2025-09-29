package org.horizon.module.knowledge.service.document;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horizon.framework.ai.core.client.ModelToolsClient;
import org.horizon.framework.ai.core.dto.embed.EmbedReq;
import org.horizon.framework.ai.core.dto.embed.EmbedResp;
import org.horizon.framework.ai.core.dto.file.FileIngestorReq;
import org.horizon.framework.ai.core.dto.file.FileIngestorResp;
import org.horizon.framework.ai.core.infer.impl.EmbedToInfer;
import org.horizon.framework.ai.core.infer.impl.FileIngestorToInfer;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.framework.tenant.core.context.TenantContextHolder;
import org.horizon.module.knowledge.api.doc.dto.FaqDTO;
import org.horizon.module.knowledge.api.doc.dto.SubmitDocReqDTO;
import org.horizon.module.knowledge.controller.admin.docs.vo.DocsSaveReqVO;
import org.horizon.module.knowledge.controller.admin.docversions.vo.DocVersionsSaveReqVO;
import org.horizon.module.knowledge.convert.categories.CategoriesConvert;
import org.horizon.module.knowledge.convert.categoryembeddings.CategoryEmbeddingConvert;
import org.horizon.module.knowledge.convert.docchunksembeddings.EmbeddingConvert;
import org.horizon.module.knowledge.convert.faqcategorylinks.FaqCategoryLinksConvert;
import org.horizon.module.knowledge.convert.faqembeddings.FaqEmbeddingIndex;
import org.horizon.module.knowledge.convert.faqembeddings.FaqEmbeddingsConvert;
import org.horizon.module.knowledge.convert.faqs.FaqConvert;
import org.horizon.module.knowledge.dal.dataobject.categories.CategoriesDO;
import org.horizon.module.knowledge.dal.dataobject.categoryembeddings.CategoryEmbeddingsDO;
import org.horizon.module.knowledge.dal.dataobject.docchunks.DocChunksDO;
import org.horizon.module.knowledge.dal.dataobject.docchunksembeddings.DocChunksEmbeddingsDO;
import org.horizon.module.knowledge.dal.dataobject.faqcategorylinks.FaqCategoryLinksDO;
import org.horizon.module.knowledge.dal.dataobject.faqembeddings.FaqEmbeddingsDO;
import org.horizon.module.knowledge.dal.dataobject.faqs.FaqsDO;
import org.horizon.module.knowledge.service.categories.CategoriesService;
import org.horizon.module.knowledge.service.categoryembeddings.CategoryEmbeddingsService;
import org.horizon.module.knowledge.service.docchunks.DocChunksService;
import org.horizon.module.knowledge.service.docchunksembeddings.DocChunksEmbeddingsService;
import org.horizon.module.knowledge.service.docs.DocsService;
import org.horizon.module.knowledge.service.docversions.DocVersionsService;
import org.horizon.module.knowledge.service.faqcategorylinks.FaqCategoryLinksService;
import org.horizon.module.knowledge.service.faqembeddings.FaqEmbeddingsService;
import org.horizon.module.knowledge.service.faqs.FaqsService;
import org.horizon.module.knowledge.service.faqs.FaqsServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import org.horizon.module.knowledge.convert.docchunks.DocChunksConvert;


import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 文档 + FAQ 数据集 Service 实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentDatasetServiceImpl implements DocumentDatasetService {

    @Resource
    @Lazy
    private final FaqsServiceImpl faqsServiceImpl;
    private final ModelToolsClient modelToolsClient;

    @Resource
    @Lazy
    private DocsService docsService;

    @Resource
    @Lazy
    private DocVersionsService docVersionsService;

    @Resource
    @Lazy
    DocChunksService docChunksService;

    @Resource
    @Lazy
    DocChunksEmbeddingsService docChunksEmbeddingsService;

    @Resource
    @Lazy
    FaqsService faqsService;

    @Resource
    @Lazy
    FaqEmbeddingsService faqEmbeddingsService;

    @Resource
    @Lazy
    CategoriesService categoriesService;

    @Resource
    @Lazy
    FaqCategoryLinksService faqCategoryLinksService;

    @Resource
    @Lazy
    CategoryEmbeddingsService categoryEmbeddingsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<Void> submit(SubmitDocReqDTO req) {
        try {


            // 顶层校验
            if (req == null) {
                return CommonResult.error(400, "请求体不能为空");
            }
            if (StrUtil.isBlank(req.getInfraFileId())) {
                return CommonResult.error(400, "文档数据库 ID 不能为空");
            }
            if (StrUtil.isBlank(req.getKey())) {
                return CommonResult.error(400, "存储 Key 不能为空");
            }
            if (CollectionUtils.isEmpty(req.getFaqs())) {
                return CommonResult.error(400, "FAQ 数据不能为空");
            }


            Long tenantId = TenantContextHolder.getTenantId();

            final String langId = "ja";
            final String displayFlag = "public";
            final String operator = String.valueOf(tenantId);
            final String namespace ="tenant:"+tenantId;
            final String provider = "ELYZA";
            final String modelVersion ="1.0";

            //File ID
            String infraFileId = req.getInfraFileId() ;
            String key =req.getKey();


            //1、Create Knowledge Base Document Version
            Long docVersionsId =createDocVersions(infraFileId);

            //2、Create Knowledge Base Document  StrUtil
            Long knowledgeDocsId=createDocs(docVersionsId,tenantId);

            //3、Update the document ID in the knowledge base document version table,
            //since a single knowledge base document may have multiple versions.
            //This facilitates tracing the original source when responding to queries.
            int index=updateKnowledgeDocsIdById(knowledgeDocsId,docVersionsId);


            //4，知识库文档切片

            FileIngestorResp fileIngestor=getfileIngestorTexts(key);

            if(ObjectUtil.isEmpty(fileIngestor)){
                return CommonResult.error(500, "切片结果为空，无法进行向量化");
            }

            // 4.1 数据转换：把ai模块返回的数据转为保存数据
            List<DocChunksDO> docChunksList =
                    DocChunksConvert.INSTANCE.fromResp(fileIngestor, docVersionsId);

            // 4.2 批量入库
            docChunksService.insertBatch(docChunksList);


            //5、文档切片向量
            //5.1 获得切片文本
            List<String> textsChunk = docChunksList.stream()
                    .map(DocChunksDO::getText)     // 提取 text 字段
                    .filter(text -> text != null && !text.isBlank()) // 过滤空值（可选）
                    .collect(Collectors.toList());

            //5.2 把数据转为ai请求参数
            EmbedReq embedChunksReq = new EmbedReq();
            embedChunksReq.setTexts(textsChunk);
            embedChunksReq.setNamespace("infra_file:" + infraFileId);
            embedChunksReq.setMetadata(java.util.Map.of(
                    "infra_file_id",infraFileId,
                    "key", key
            ));
            //5.3 切片向量转换
            EmbedResp m_EmbedChunksResp= modelToolsClient.generate(
                    new EmbedToInfer().convert(embedChunksReq),
                    EmbedResp.class
            );

            //5.4  调用工具类，把 EmbedResp 转换为 DO 列表

            List<Long> chunkIds = docChunksList.stream()
                    .map(DocChunksDO::getId)
                    .map(Long::valueOf)
                    .collect(Collectors.toList());



            List<DocChunksEmbeddingsDO> batch = EmbeddingConvert.toDOList(
                    chunkIds,      // List<Long>，与 m_EmbedResp.getEmbeddings() 一一对应
                    m_EmbedChunksResp,   // EmbedResp 对象
                    provider,      // 比如 "ELYZA" / "voyage"
                    namespace,     // 比如 "tenant:t1|kb:leaf-labour"
                    operator       // 当前操作人
            );
            docChunksEmbeddingsService.insertBatch(batch);

           //6 FAQ主表
            List<FaqDTO> faqs = req.getFaqs();

            if(ObjectUtil.isEmpty(faqs)){
                return CommonResult.error(500, "FAQ结果为空，无法进行向量化");
            }


            // ===== 转换成 DO 列表 =====
            List<FaqsDO> faqDOList = FaqConvert.toDOList(
                    faqs,
                    null,      // 关联的知识库节点ID
                    tenantId,
                    docVersionsId,   // 文档版本ID
                    "manual",    // sourceType，例如 "manual" / "doc" / "import" / "auto"
                    langId,        // langId，例如 "ja" 或 "en"
                    operator     // 当前操作人
            );

            faqsService.insertBatch(faqDOList);

            // 7 FAQ向量
            List<String> textsFaq = new ArrayList<>();
            List<FaqEmbeddingIndex> indexFaq = new ArrayList<>(); // 与 texts 一一对应

            for (FaqsDO faq : faqDOList) {
                Long faqId = faq.getId();
                String q = faq.getQuestion();
                String a = faq.getAnswer();

                if (q != null && !q.isBlank()) {
                    textsFaq.add(q);
                    indexFaq.add(new FaqEmbeddingIndex(faqId, 0)); // 0=question
                }
                if (a != null && !a.isBlank()) {
                    textsFaq.add(a);
                    indexFaq.add(new FaqEmbeddingIndex(faqId, 1)); // 1=answer
                }
            }

            // 2) 构造 EmbedReq
            EmbedReq embedFaqReq = new EmbedReq();
            embedFaqReq.setTexts(textsFaq);
            // 命名空间随场景自定，示例：租户维度/业务域
            embedFaqReq.setNamespace("tenant:" + tenantId + "|faq");
            embedFaqReq.setMetadata(Map.of(
                    "tenant_id", tenantId,
                    "source", "faq" // 方便后续追踪
            ));
            // 3) 调用向量服务
            EmbedResp embedFaqResp = modelToolsClient.generate(
                    new EmbedToInfer().convert(embedFaqReq),
                    EmbedResp.class
            );

            // 4) 结果校验
            List<List<Float>> embList = embedFaqResp.getEmbeddings();  // 或你项目里的向量类型
            if (embList == null || embList.size() != indexFaq.size()) {
                throw new IllegalStateException("嵌入向量数量与输入文本数量不一致");
            }



            // 5) 还原为向量 DO（与 texts/index 顺序一致）
            List<FaqEmbeddingsDO> FaqEmbeddingsList = FaqEmbeddingsConvert.toDOList(
                    indexFaq,          // 与 embList 一一对应
                    embList,
                    provider,
                    modelVersion,
                    provider,
                    namespace,
                    operator
            );
            // 6) 批量入库
            faqEmbeddingsService.insertBatch(FaqEmbeddingsList);


            //8 FAQ分类
            List<CategoriesDO> candidates = new ArrayList<>();
            for (FaqsDO faq : faqDOList) {
                Long faqId = faq.getId();
                if (faqId == null) continue;

                for (String raw : faq.getCategories()) {
                    String name = raw == null ? null : raw.trim();
                    if (name == null || name.isEmpty()) continue;

                    CategoriesDO categories = CategoriesConvert.buildCategoryDO(
                            faqId,
                            name,                   // 分类名
                            langId,                   // 语言ID
                            "public",               // 显示标志
                            String.valueOf(tenantId) // 当前操作人
                    );

                    candidates.add(categories);
                }
            }

            categoriesService.upsertAndFillIds(langId,candidates,operator);

            //9 FAQ分类关联表
            List<FaqCategoryLinksDO> links = FaqCategoryLinksConvert.toLinks(
                    tenantId,
                    candidates,
                    operator
            );

            faqCategoryLinksService.insertBatch(links);


            // ---------- 10 分类向量 ----------

            // 10.0) 先把 (规范化后的分类名 -> 分类ID) 建好（保持插入后的 ID）
            Map<String, Long> nameToId = new LinkedHashMap<>();
            for (CategoriesDO c : candidates) { // 这一步要求 categoriesWithIds 的每个对象都已回填了 id
                if (c == null || c.getQuestion() == null) continue;
                String normalized = c.getQuestion().trim();
                if (normalized.isEmpty()) continue;
                // 若同名出现多次，保留第一个（保持顺序 + 避免重名导致 embeddings 对齐出错）
                nameToId.putIfAbsent(normalized, c.getId());
            }

            // 10.1) texts 与 ids 用同一来源与顺序，确保一一对应
            List<String> categoryTexts = new ArrayList<>(nameToId.keySet());
            List<Long> categoryIds = new ArrayList<>(nameToId.values());
            if (categoryTexts.isEmpty()) {
                // 没有可嵌入的分类，直接跳过
                // return; 或者继续后续流程但不做向量
            }

            // 10.2) 构造 EmbedReq
            EmbedReq embedCategoriesReq = new EmbedReq();
            embedCategoriesReq.setTexts(categoryTexts);
            String categoryNamespace = "tenant:" + tenantId + "|kb:category";
            embedCategoriesReq.setNamespace(categoryNamespace);
            embedCategoriesReq.setMetadata(Map.of(
                    "tenant_id", String.valueOf(tenantId),
                    "lang_id", langId,
                    "source", "faq_category"
            ));

            // 10.3) 调用向量服务
            EmbedResp m_EmbedCategoriesResp = modelToolsClient.generate(
                    new EmbedToInfer().convert(embedCategoriesReq),
                    EmbedResp.class
            );

            // 10.4) 解析/兜底模型信息（CategoryEmbeddingConvert 不从 resp 读模型信息）
            String embedModelName =
                    m_EmbedCategoriesResp.getModel() != null && !m_EmbedCategoriesResp.getModel().isBlank()
                            ? m_EmbedCategoriesResp.getModel().trim()
                            : provider;                 // 兜底：至少用 provider


            // 10.5) 转换为 DO 列表（严格校验：categoryIds.size 必须等于 embeddings.size）
            List<CategoryEmbeddingsDO> catEmbeds = CategoryEmbeddingConvert.toDOListFromResp(
                    categoryIds,
                    m_EmbedCategoriesResp,
                    embedModelName,
                    modelVersion,
                    provider,
                    categoryNamespace,
                    operator
            );

            // 10.6) 批量落库
            if (catEmbeds != null && !catEmbeds.isEmpty()) {
                categoryEmbeddingsService.insertBatch(catEmbeds);
            }



            return CommonResult.success(null);

        } catch (Exception ex) {
            log.error("[submit] 异常", ex);

            return CommonResult.error(500, "提交失败：" + ex.getMessage());
        }
    }

    private FileIngestorResp getfileIngestorTexts(String key){
        //4，知识库文档切片

        //4.1 文档切片
        FileIngestorReq fileIngestorReq = new FileIngestorReq();
        fileIngestorReq.setBucket("rag-dev");
        fileIngestorReq.setKey(key);

        FileIngestorResp fileIngestorResp = modelToolsClient.generate(
                new FileIngestorToInfer().convert(fileIngestorReq),
                FileIngestorResp.class
        );

        return fileIngestorResp;
    }

    private int updateKnowledgeDocsIdById(Long knowledgeDocsId,Long doVersionsId){

        DocVersionsSaveReqVO createReqVODocVersions = new DocVersionsSaveReqVO();
        createReqVODocVersions.setId(doVersionsId);
        createReqVODocVersions.setKnowledgeDocsId(knowledgeDocsId);
        int index =docVersionsService.updateKnowledgeDocsIdById(createReqVODocVersions);
        return index;
    }

    /**
     *
     * @return Knowledge Base Document Version ID
     */
    private Long createDocVersions(String infraFileId){

        DocVersionsSaveReqVO createReqVODocVersions = new DocVersionsSaveReqVO();
        createReqVODocVersions.setInfraFileId(Long.valueOf(infraFileId));
        Long id =docVersionsService.createDocVersions(createReqVODocVersions);

        return id;
    }

    /**
     *
     * @param docVersionsId
     * @param tenantId
     * @return
     */
    private Long createDocs(Long docVersionsId,Long tenantId){
        DocsSaveReqVO createReqVODocs = new DocsSaveReqVO();
        //Tenant ID：
        createReqVODocs.setTenantId(tenantId);
        //node id：
        createReqVODocs.setNodeId(111L);
        //Active Version ID：
        createReqVODocs.setActiveVersionId(docVersionsId);
        //Contributor Name
        createReqVODocs.setAttributionName("test");
        //Display Flag
        createReqVODocs.setDisplayFlag(1);
        //Remarks
        createReqVODocs.setRemark("test");
        //Create Knowledge Base Document
        Long id = docsService.createDocs(createReqVODocs);
        return id;
    }

    /** FAQ 简单规整 */
    private FaqDTO normalizeFaq(FaqDTO faq) {
        faq.setQuestion(StrUtil.trimToEmpty(faq.getQuestion()));
        faq.setCategories(faq.getCategories().stream()
                .map(StrUtil::trimToEmpty)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList()));
        faq.setAnswer(StrUtil.trimToEmpty(faq.getAnswer()));
        return faq;
    }

}