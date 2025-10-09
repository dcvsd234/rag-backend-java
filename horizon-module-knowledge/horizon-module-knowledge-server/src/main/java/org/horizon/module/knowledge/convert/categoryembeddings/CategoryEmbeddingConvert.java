package org.horizon.module.knowledge.convert.categoryembeddings;

import org.horizon.framework.ai.core.dto.embed.EmbedResp;
import org.horizon.module.knowledge.dal.dataobject.categoryembeddings.CategoryEmbeddingsDO;
import org.horizon.module.knowledge.util.VectorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类向量 DO 转换器（解耦版）
 * - 不从 EmbedResp 解析模型元信息；由调用方传入（配置更权威）
 * - 提供将 EmbedResp 中的向量安全转换为 float[] 的便捷重载
 */
public class CategoryEmbeddingConvert {

    private CategoryEmbeddingConvert() {}

    /**
     * 将 分类ID 列表 + 向量列表 生成 CategoryEmbeddingsDO 列表
     *
     * @param categoryIds   分类ID列表（与 embList 一一对应）
     * @param embList       向量结果（每条 List<Float>）
     * @param modelName     向量模型名（从配置/注册表获取）
     * @param modelVersion  模型版本号（从配置/注册表获取，缺省可传 "n/a"）
     * @param provider      模型提供方（如 "ELYZA"）
     * @param namespace     命名空间（如 "tenant:1|kb:category"）
     * @param operator      操作者
     */
    public static List<CategoryEmbeddingsDO> toDOList(
            List<Long> categoryIds,
            List<List<Float>> embList,
            String modelName,
            String modelVersion,
            String provider,
            String namespace,
            String operator
    ) {
        if (categoryIds == null || embList == null || categoryIds.size() != embList.size()) {
            throw new IllegalArgumentException("categoryIds 和 embList 长度不一致或为空");
        }
        final List<CategoryEmbeddingsDO> toSave = new ArrayList<>(embList.size());
        for (int i = 0; i < embList.size(); i++) {
            float[] vec = VectorUtils.toFloatArrayStrict(embList.get(i));
            CategoryEmbeddingsDO e = CategoryEmbeddingsDO.builder()
                    .id(null)
                    .categoryId(categoryIds.get(i))
                    .modelName(modelName)
                    .modelVersion(modelVersion)
                    .provider(provider)
                    .namespace(namespace)
                    .metric("cosine")     // 与库内约定一致；需要可做成入参
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
     * 便捷重载：若手头是 EmbedResp，仅提取 embeddings；
     * 模型/度量信息仍由上层传入，职责更清晰。
     */
    public static List<CategoryEmbeddingsDO> toDOListFromResp(
            List<Long> categoryIds,
            EmbedResp resp,
            String modelName,
            String modelVersion,
            String provider,
            String namespace,
            String operator
    ) {
        List<List<Float>> raw = (resp != null) ? resp.getEmbeddings() : null;
        if (raw == null || raw.isEmpty()) return List.of();
        if (categoryIds == null || categoryIds.size() != raw.size()) {
            throw new IllegalArgumentException("categoryIds.size != resp.embeddings.size");
        }
        return toDOList(categoryIds, raw, modelName, modelVersion, provider, namespace, operator);
    }
}