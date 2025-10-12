package org.horizon.framework.ai.core.dto.infer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 与 Python InferenceRequest 完全一致：
 * 顶层：tenant_id / trace_id / lang_id / input / parameters
 * parameters：任意键值（snake_case）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InferRequest {

    // —— BaseRequest 等价字段（扁平到顶层）——
    @JsonProperty("tenant_id") private String tenantId;
    @JsonProperty("trace_id")  private String traceId;
    @JsonProperty("lang_id")   private Object langId;

    /** 用户输入（问题/文本/政策原文等） */
    private String input;

    /** 任意推理参数字典（snake_case） */
    private Map<String, Object> parameters;

    /** 便捷：确保 parameters 非空 */
    public Map<String, Object> ensureParams() {
        if (parameters == null) parameters = new LinkedHashMap<>();
        return parameters;
    }
}