package org.horizon.framework.ai.core.infer.impl;


import org.horizon.framework.ai.core.dto.generate.GenerateReq;
import org.horizon.framework.ai.core.dto.generate.GenParams;
import org.horizon.framework.ai.core.dto.infer.InferRequest;
import org.horizon.framework.ai.core.infer.ToInferRequest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * GenerateReq → InferRequest 转换器
 * - 用于将文本生成/改写请求映射为 model_zoo 的统一 /infer 协议
 * - 目标后端：generator_service
 *
 * 映射要点：
 * - InferRequest.input 承载 GenerateReq.input（在配置中心映射为 policy_text）
 * - parameters 写入：
 *   - 必填：config_key=generator_service, pipeline_name
 *   - 常用：audience, template, template_vars, post_remove_phrases
 *   - 采样：gen_params（由 GenParams 对象转 snake_case Map）
 *   - 其他可选：instruction, tone, language, output_format, max_words, context, output_mode, return_raw
 */
public class GeneratorToInfer implements ToInferRequest<GenerateReq> {

    @Override
    public String configKey() {
        return "generator_service";
    }

    @Override
    public void validate(GenerateReq src) {
        if (isEmpty(src.getInput())) {
            throw new IllegalArgumentException("input is required");
        }
        if (isEmpty(src.getPipelineName())) {
            // 配置中心对 generator_service 要求 pipeline_name 为必填
            throw new IllegalArgumentException("pipeline_name is required");
        }
    }

    @Override
    public InferRequest convert(GenerateReq src) {
        validate(src);

        Map<String, Object> params = new LinkedHashMap<>();
        // 必填
        params.put("config_key", configKey());
        params.put("pipeline_name", src.getPipelineName());

        // 常用可选
        if (!isEmpty(src.getAudience()))            params.put("audience", src.getAudience());
        if (!isEmpty(src.getTemplate()))            params.put("template", src.getTemplate());
        if (notEmpty(src.getTemplateVars()))        params.put("template_vars", src.getTemplateVars());
        if (notEmpty(src.getPostRemovePhrases()))   params.put("post_remove_phrases", src.getPostRemovePhrases());

        // 采样参数（整体优先）
        if (src.getGenParams() != null) {
            Map<String, Object> gp = genParamsToMap(src.getGenParams());
            if (!gp.isEmpty()) params.put("gen_params", gp);
        }

        // 其他控制项（下游若支持可用，否则可忽略；保持透传以增强通用性）
        if (!isEmpty(src.getInstruction()))         params.put("instruction", src.getInstruction());
        if (!isEmpty(src.getTone()))                params.put("tone", src.getTone());
        if (!isEmpty(src.getLanguage()))            params.put("language", src.getLanguage());
        if (src.getOutputFormat() != null)          params.put("output_format", src.getOutputFormat().name());
        if (src.getMaxWords() != null)              params.put("max_words", src.getMaxWords());
        if (notEmpty(src.getContext()))             params.put("context", src.getContext());
        if (!isEmpty(src.getOutputMode()))          params.put("output_mode", src.getOutputMode());
        if (src.getReturnRaw() != null)             params.put("return_raw", src.getReturnRaw());

        return InferRequest.builder()
                .tenantId(src.getTenantId())
                .traceId(src.getTraceId())
                .langId(src.getLangId())
                .input(src.getInput())     // 在配置中心映射为 policy_text
                .parameters(params)
                .build();
    }

    private static Map<String, Object> genParamsToMap(GenParams gp) {
        Map<String, Object> m = new LinkedHashMap<>();
        if (gp.getMaxTokens() != null)      m.put("max_tokens", gp.getMaxTokens());
        if (gp.getTemperature() != null)    m.put("temperature", gp.getTemperature());
        if (gp.getTopP() != null)           m.put("top_p", gp.getTopP());
        if (gp.getTopK() != null)           m.put("top_k", gp.getTopK());
        if (gp.getRepeatPenalty() != null)  m.put("repeat_penalty", gp.getRepeatPenalty());
        if (gp.getStop() != null && !gp.getStop().isEmpty()) m.put("stop", gp.getStop());
        if (gp.getSeed() != null)           m.put("seed", gp.getSeed());
        return m;
    }

    private static boolean isEmpty(String s) { return s == null || s.isEmpty(); }
    private static boolean notEmpty(Map<?, ?> m) { return m != null && !m.isEmpty(); }
    private static boolean notEmpty(List<?> l) { return l != null && !l.isEmpty(); }
}