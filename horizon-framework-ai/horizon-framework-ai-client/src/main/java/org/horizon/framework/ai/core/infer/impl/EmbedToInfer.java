package org.horizon.framework.ai.core.infer.impl;

import org.horizon.framework.ai.core.dto.embed.EmbedReq;
import org.horizon.framework.ai.core.dto.infer.InferRequest;
import org.horizon.framework.ai.core.infer.ToInferRequest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * EmbedReq → InferRequest 转换器
 * - 用于将 embedding 请求转为 model_zoo 的统一 /infer 请求格式
 */
public class EmbedToInfer implements ToInferRequest<EmbedReq> {

    @Override
    public String configKey() {
        return "embedding_service"; // 配置中心的 key
    }

    @Override
    public void validate(EmbedReq src) {
        if (src.getTexts() == null || src.getTexts().isEmpty()) {
            throw new IllegalArgumentException("texts required (must contain at least one string)");
        }
    }

    @Override
    public InferRequest convert(EmbedReq src) {
        validate(src);

        Map<String, Object> p = new LinkedHashMap<>();
        p.put("config_key", configKey());
        p.put("texts", src.getTexts());
        if (src.getNamespace() != null) {
            p.put("namespace", src.getNamespace());
        }
        if (src.getMetadata() != null && !src.getMetadata().isEmpty()) {
            p.put("metadata", src.getMetadata());
        }

        return InferRequest.builder()
                .tenantId(src.getTenantId())
                .traceId(src.getTraceId())
                .langId(src.getLangId())
                .input(src.getTexts().get(0))  // input → 第一条文本，保证 model_zoo input 不为空
                .parameters(p)
                .build();
    }
}