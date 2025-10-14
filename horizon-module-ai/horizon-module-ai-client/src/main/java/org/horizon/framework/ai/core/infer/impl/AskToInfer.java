package org.horizon.framework.ai.core.infer.impl;

import org.horizon.framework.ai.core.dto.ragorchestrator.AskReq;
import org.horizon.framework.ai.core.dto.infer.InferRequest;
import org.horizon.framework.ai.core.infer.ToInferRequest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * AskReq → InferRequest 转换器
 * - 将多语言问答请求转换为统一模型推理请求结构
 */
public class AskToInfer implements ToInferRequest<AskReq> {

    @Override
    public String configKey() {
        return "rag_orchestrator";
    }

    @Override
    public void validate(AskReq src) {
        if (src.getQuestion() == null || src.getQuestion().isEmpty()) {
            throw new IllegalArgumentException("question is required");
        }
    }

    @Override
    public InferRequest convert(AskReq src) {
        validate(src);

        Map<String, Object> p = new LinkedHashMap<>();
        p.put("config_key", configKey());
        p.put("question", src.getQuestion());
        p.put("session_id", src.getSessionId());

        return InferRequest.builder()
                .tenantId(src.getTenantId())
                .traceId(src.getTraceId())
                .langId(src.getLangId())
                .input(src.getQuestion())  // input 仍然用于统一模型输入
                .parameters(p)
                .build();
    }
}