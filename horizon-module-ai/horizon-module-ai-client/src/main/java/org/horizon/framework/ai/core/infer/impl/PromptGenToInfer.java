package org.horizon.framework.ai.core.infer.impl;


import org.horizon.framework.ai.core.dto.infer.InferRequest;
import org.horizon.framework.ai.core.dto.prompt.PromptReq;
import org.horizon.framework.ai.core.infer.ToInferRequest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * PromptReq → InferRequest 转换器
 * - 将 FAQ 三要素 (question, answer, categories) 等信息
 *   转换为 model_zoo 的统一 /infer 请求格式
 */
public class PromptGenToInfer implements ToInferRequest<PromptReq> {

    @Override
    public String configKey() {
        return "prompt_gen";
    }

    @Override
    public void validate(PromptReq src) {
        if (src.getPipelineName() == null || src.getPipelineName().isEmpty()) {
            throw new IllegalArgumentException("pipeline_name is required");
        }
        if (src.getQuestion() == null || src.getQuestion().isEmpty()) {
            throw new IllegalArgumentException("question is required");
        }
        if (src.getAnswer() == null || src.getAnswer().isEmpty()) {
            throw new IllegalArgumentException("answer is required");
        }
        List<String> cats = src.getCategories();
        if (cats == null || cats.isEmpty()) {
            throw new IllegalArgumentException("categories is required (non-empty list)");
        }
    }

    @Override
    public InferRequest convert(PromptReq src) {
        validate(src);

        Map<String, Object> params = new LinkedHashMap<>();
        // 必填
        params.put("config_key", configKey());
        params.put("pipeline_name", src.getPipelineName());
        params.put("answer", src.getAnswer());
        params.put("categories", src.getCategories());

        // 可选：受众 & 模板
        if (notEmpty(src.getAudience()))      params.put("audience", src.getAudience());
        if (notEmpty(src.getTemplate()))      params.put("template", src.getTemplate());
        if (notEmpty(src.getTemplateVars()))  params.put("template_vars", src.getTemplateVars());

        // 可选：扁平采样控制（如果你希望也透传；gen_params 优先级更高）
        if (src.getMaxTokens() != null)       params.put("max_tokens", src.getMaxTokens());
        if (src.getTemperature() != null)     params.put("temperature", src.getTemperature());
        if (src.getTopP() != null)            params.put("top_p", src.getTopP());
        if (src.getTopK() != null)            params.put("top_k", src.getTopK());
        if (src.getRepeatPenalty() != null)   params.put("repeat_penalty", src.getRepeatPenalty());
        if (notEmpty(src.getStop()))          params.put("stop", src.getStop());
        if (src.getSeed() != null)            params.put("seed", src.getSeed());

        // 可选：整体采样参数（优先级最高）
        if (notEmpty(src.getGenParams()))     params.put("gen_params", src.getGenParams());

        return InferRequest.builder()
                .tenantId(src.getTenantId())
                .traceId(src.getTraceId())
                .langId(src.getLangId())
                .input(src.getQuestion())      // input → FAQ.question
                .parameters(params)
                .build();
    }

    private static boolean notEmpty(String s) {
        return s != null && !s.isEmpty();
    }
    private static boolean notEmpty(Map<?,?> m) {
        return m != null && !m.isEmpty();
    }
    private static boolean notEmpty(List<?> l) {
        return l != null && !l.isEmpty();
    }
}