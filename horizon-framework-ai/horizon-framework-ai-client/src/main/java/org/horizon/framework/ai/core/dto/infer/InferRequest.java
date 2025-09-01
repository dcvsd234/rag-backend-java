package org.horizon.framework.ai.core.dto.infer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.horizon.framework.ai.core.dto.generate.GenParams;

import java.util.List;
import java.util.Map;

/**
 * /infer 请求 DTO
 * - 顶层字段：input
 * - parameters 下包含所有推理参数（snake_case）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InferRequest {

    /** 顶层：用户输入的问题/文本 */
    private String input;

    /** 顶层：推理参数 */
    private Parameters parameters;

    // -------------------- inner DTO --------------------
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Parameters {
        private String config_key;           // 例如：generator_service
        private String pipeline_name;        // 例如：policy
        private String audience;             // 例如：学生 / student
        private String trace_id;             // 链路追踪 ID
        private String tenant_id;            // 租户 ID
        private Object lang_id;              // 语言 ID，可 int / string

        private String template;             // 模板字符串
        private Map<String, Object> template_vars; // 模板变量
        private List<String> post_remove_phrases;  // 后处理短语
        private GenParams gen_params;        // ✅ 直接复用已有的 GenParams
    }
}