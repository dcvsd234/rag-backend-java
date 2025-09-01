package org.horizon.framework.ai.core.dto.generate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;

/** 文本生成/重写 - 响应 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateResp {

    /** 模型生成的最终文本 */
    private String text;

    /** 可选：生成耗时（毫秒） */
    private Long durationMs;

    /** 可选：使用的模型标识（如 ELYZA-japanese-Llama-2-7b） */
    private String model;

    /** 可选：token 使用信息（便于统计） */
    private Usage usage;

    /** 可选：扩展元信息（可放 pipelineName / ragHit 等） */
    private Map<String, Object> meta;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {
        /** 输入 token 数 */
        private Integer promptTokens;
        /** 输出 token 数 */
        private Integer completionTokens;
        /** 总 token 数 */
        private Integer totalTokens;
    }
}