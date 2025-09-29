package org.horizon.module.knowledge.convert.docchunksembeddings;

import org.horizon.framework.ai.core.dto.embed.EmbedResp;
import org.horizon.module.knowledge.dal.dataobject.docchunksembeddings.DocChunksEmbeddingsDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmbeddingConvert {

    private EmbeddingConvert() {}

    public static List<DocChunksEmbeddingsDO> toDOList(
            List<Long> chunkIds,
            EmbedResp resp,
            String provider,
            String namespace,
            String operator
    ) {
        List<List<Float>> embeddings = resp.getEmbeddings();
        if (embeddings == null || embeddings.isEmpty()) {
            return List.of();
        }
        if (chunkIds == null || chunkIds.size() != embeddings.size()) {
            throw new IllegalArgumentException("chunkIds.size != embeddings.size");
        }

        // --- 关键修复：稳健获取 model_name / model_version，并做兜底 ---
        final String modelName    = coalesceModelName(resp, provider); // 不为空
        final String modelVersion = coalesceModelVersion(resp);        // 可为空时给 "n/a"
        final String metric       = coalesceMetric(resp, "cosine");    // 默认 cosine

        List<DocChunksEmbeddingsDO> result = new ArrayList<>(embeddings.size());
        for (int i = 0; i < embeddings.size(); i++) {
            float[] arr = toFloatArrayStrict(embeddings.get(i));
            DocChunksEmbeddingsDO e = DocChunksEmbeddingsDO.builder()
                    .chunkId(chunkIds.get(i))
                    .modelName(modelName)       // 保证不为 null
                    .modelVersion(modelVersion) // 有兜底
                    .provider(provider)
                    .namespace(namespace)
                    .metric(metric)
                    .dim(arr.length)
                    .embedding(arr)
                    .build();
            // 父类字段用 setter（你当前是 @Builder 在子类）
            e.setDeleted(false);
            e.setCreator(operator);
            e.setUpdater(operator);

            result.add(e);
        }
        return result;
    }

    /** 从 EmbedResp 各处提取模型名；都取不到则回退为 provider，避免 NOT NULL 违约 */
    private static String coalesceModelName(EmbedResp resp, String provider) {
        String top = safeTrim(resp.getModel()); // InferResponse.model
        if (notBlank(top)) return top;

        Map<String, Object> meta = resp.getMetadata();
        if (meta != null) {
            Object m = firstNotBlank(meta.get("model_name"), meta.get("model"));
            if (m != null) return m.toString().trim();
        }
        // 兼容 additionalProperties（InferResponse.extra）
        Map<String, Object> extra = resp.getAdditionalProperties();
        if (extra != null) {
            Object m = firstNotBlank(extra.get("model_name"), extra.get("model"));
            if (m != null) return m.toString().trim();
        }
        // 兜底：至少写入 provider，保证非空
        return provider != null ? provider : "unknown";
    }

    /** 从 metadata / extra 中提取 model_version；取不到给 "n/a" */
    private static String coalesceModelVersion(EmbedResp resp) {
        Map<String, Object> meta = resp.getMetadata();
        if (meta != null) {
            Object v = firstNotBlank(meta.get("model_version"), meta.get("version"));
            if (v != null) return v.toString().trim();
        }
        Map<String, Object> extra = resp.getAdditionalProperties();
        if (extra != null) {
            Object v = firstNotBlank(extra.get("model_version"), extra.get("version"));
            if (v != null) return v.toString().trim();
        }
        return "n/a";
    }

    /** metric 的稳健获取（默认 cosine） */
    private static String coalesceMetric(EmbedResp resp, String defaultMetric) {
        Map<String, Object> meta = resp.getMetadata();
        if (meta != null) {
            Object m = meta.get("metric");
            if (m != null && m.toString().trim().length() > 0) return m.toString().trim();
        }
        return defaultMetric;
    }

    /** List<Float> -> float[]，校验 null/NaN/Inf */
    private static float[] toFloatArrayStrict(List<Float> vec) {
        if (vec == null || vec.isEmpty()) {
            throw new IllegalArgumentException("Empty embedding");
        }
        float[] arr = new float[vec.size()];
        for (int j = 0; j < vec.size(); j++) {
            Float v = vec.get(j);
            if (v == null || !Float.isFinite(v)) {
                throw new IllegalArgumentException("Embedding contains null/NaN/Infinity at index " + j);
            }
            arr[j] = v;
        }
        return arr;
    }

    private static Object firstNotBlank(Object a, Object b) {
        if (a != null && a.toString().trim().length() > 0) return a;
        if (b != null && b.toString().trim().length() > 0) return b;
        return null;
    }
    private static boolean notBlank(String s) { return s != null && s.trim().length() > 0; }
    private static String safeTrim(String s) { return s == null ? null : s.trim(); }
}