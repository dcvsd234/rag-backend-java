package org.horizon.framework.ai.core.dto.generate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.horizon.framework.ai.core.dto.infer.InferResponse;

import java.util.Map;

/**
 * 文本生成/重写 - 响应
 * 继承 InferResponse，复用 text / model / runtimeMs / metadata 等通用字段
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateResp extends InferResponse {

    /** 可选：生成耗时（毫秒） */
    private Long durationMs;

    /** 可选：token 使用信息（便于统计） */
    private Usage usage;

    /** 可选：扩展元信息（如 pipelineName / ragHit 等） */
    private Map<String, Object> meta;

}