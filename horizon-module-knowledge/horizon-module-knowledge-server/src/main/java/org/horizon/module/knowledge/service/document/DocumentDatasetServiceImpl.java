package org.horizon.module.knowledge.service.document;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horizon.framework.ai.core.client.ModelToolsClient;
import org.horizon.framework.ai.core.dto.embed.EmbedReq;
import org.horizon.framework.ai.core.dto.embed.EmbedResp;
import org.horizon.framework.ai.core.dto.file.Chunk;
import org.horizon.framework.ai.core.dto.file.FileIngestorReq;
import org.horizon.framework.ai.core.dto.file.FileIngestorResp;
import org.horizon.framework.ai.core.infer.impl.EmbedToInfer;
import org.horizon.framework.ai.core.infer.impl.FileIngestorToInfer;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.knowledge.api.doc.dto.FaqDTO;
import org.horizon.module.knowledge.api.doc.dto.SubmitDocReqDTO;
import org.horizon.module.knowledge.service.faq.FaqPersistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文档 + FAQ 数据集 Service 实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentDatasetServiceImpl implements DocumentDatasetService {

    private final FaqPersistService faqPersistService;
    private final ModelToolsClient modelToolsClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<Void> submit(SubmitDocReqDTO req) {
        try {
            log.info("[submit] dbId={}, key={}, faqCount={}",
                    req == null ? null : req.getDbId(),
                    req == null ? null : req.getKey(),
                    req == null || req.getFaqs() == null ? 0 : req.getFaqs().size());

            // 顶层校验
            if (req == null) {
                return CommonResult.error(400, "请求体不能为空");
            }
            if (StrUtil.isBlank(req.getDbId())) {
                return CommonResult.error(400, "文档数据库 ID 不能为空");
            }
            if (StrUtil.isBlank(req.getKey())) {
                return CommonResult.error(400, "存储 Key 不能为空");
            }
            if (CollectionUtils.isEmpty(req.getFaqs())) {
                return CommonResult.error(400, "FAQ 数据不能为空");
            }

            // 轻量规整：去空格、过滤空分类
            List<FaqDTO> normalized = req.getFaqs().stream()
                    .map(this::normalizeFaq)
                    .collect(Collectors.toList());

            // 1. 文档切片
            FileIngestorReq fileIngestorReq = new FileIngestorReq();
            fileIngestorReq.setBucket("rag-dev");
            fileIngestorReq.setKey(req.getKey());

            FileIngestorResp fileIngestorResp = modelToolsClient.generate(
                    new FileIngestorToInfer().convert(fileIngestorReq),
                    FileIngestorResp.class
            );

            // 2. 从切片结果提取文本
            List<String> texts = fileIngestorResp.getChunks().stream()
                    .map(Chunk::getText)                 // ✅ 直接用顶级类名
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.toList());

            if (texts.isEmpty()) {
                return CommonResult.error(500, "切片结果为空，无法进行向量化");
            }

            // 3. 文本向量化
            EmbedReq embedReq = new EmbedReq();
            embedReq.setTexts(texts);
            embedReq.setNamespace("db:" + req.getDbId());
            embedReq.setMetadata(java.util.Map.of(
                    "db_id", req.getDbId(),
                    "key", req.getKey()
            ));

            EmbedResp m_EmbedResp= modelToolsClient.generate(
                    new EmbedToInfer().convert(embedReq),
                    EmbedResp.class
            );

            // 4. 提示词转化



            // 5. 持久化 FAQ
            //faqPersistService.saveFaqs(req.getDbId(), req.getKey(), normalized);

            return CommonResult.success(null);

        } catch (Exception ex) {
            log.error("[submit] 异常", ex);

            return CommonResult.error(500, "提交失败：" + ex.getMessage());
        }
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