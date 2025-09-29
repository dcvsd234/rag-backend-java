package org.horizon.module.knowledge.convert.faqembeddings;

import org.horizon.framework.ai.core.dto.embed.EmbedResp;
import org.horizon.module.knowledge.dal.dataobject.faqembeddings.FaqEmbeddingsDO;
import org.horizon.module.knowledge.util.VectorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * FAQ 向量 DO 转换器（解耦版）
 * - 不从 EmbedResp 解析模型元信息；由调用方传入（配置更权威）
 * - 提供将 EmbedResp 中的向量安全转换为 float[] 的便捷重载
 */
public class FaqEmbeddingsConvert {

    private FaqEmbeddingsConvert() {}

    /**
     * 将索引信息 + 向量列表 生成 FaqEmbeddingsDO 列表（推荐入口；与 HTTP 响应解耦）
     *
     * @param index         FaqEmbeddingIndex 列表（faqId、qaRole 与向量一一对应）
     * @param embList       向量结果（每条 List<Float>）
     * @param modelName     向量模型名（从配置/注册表获取）
     * @param modelVersion  模型版本号（从配置/注册表获取，缺省可传 "n/a"）
     * @param provider      模型提供方（如 "ELYZA"）
     * @param namespace     命名空间（如 "tenant:1|faq"）
     * @param operator      操作者
     */
    public static List<FaqEmbeddingsDO> toDOList(
            List<FaqEmbeddingIndex> index,
            List<List<Float>> embList,
            String modelName,
            String modelVersion,
            String provider,
            String namespace,
            String operator
    ) {
        if (index == null || embList == null || index.size() != embList.size()) {
            throw new IllegalArgumentException("index 和 embList 长度不一致或为空");
        }
        final List<FaqEmbeddingsDO> toSave = new ArrayList<>(embList.size());
        for (int i = 0; i < embList.size(); i++) {
            FaqEmbeddingIndex ix = index.get(i);
            float[] vec = VectorUtils.toFloatArrayStrict(embList.get(i)); // ✅ 转换
            // 维度元信息：若表固定 2048，可直接写 2048；这里用实际长度以增强兼容性
            FaqEmbeddingsDO e = FaqEmbeddingsDO.builder()
                    .id(null)
                    .faqId(ix.getFaqId())
                    .qaRole(ix.getQaRole())        // 0=question, 1=answer
                    .modelName(modelName)
                    .modelVersion(modelVersion)
                    .provider(provider)
                    .namespace(namespace)
                    .metric("cosine")              // 与表定义一致，或由上层传入参数
                    .dim(vec.length)
                    .embedding(vec)
                    .build();

            e.setDeleted(false);
            e.setCreator(operator);
            e.setUpdater(operator);
            toSave.add(e);
        }
        return toSave;
    }

    /**
     * 便捷重载：如果手头是 EmbedResp，则仅用于提取 embeddings，
     * 不从 EmbedResp 解析 model/metric，仍由调用方传入，保持职责清晰。
     */
    public static List<FaqEmbeddingsDO> toDOListFromResp(
            List<FaqEmbeddingIndex> index,
            EmbedResp resp,
            String modelName,
            String modelVersion,
            String provider,
            String namespace,
            String operator
    ) {
        List<List<Float>> raw = resp != null ? resp.getEmbeddings() : null;
        if (raw == null || raw.isEmpty()) {
            return List.of();
        }
        if (index == null || index.size() != raw.size()) {
            throw new IllegalArgumentException("index.size != resp.embeddings.size");
        }
        return toDOList(index, raw, modelName, modelVersion, provider, namespace, operator);
    }

}