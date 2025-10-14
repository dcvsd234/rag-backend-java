package org.horizon.framework.ai.core.dto.prompt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.horizon.framework.ai.core.dto.infer.InferResponse;

/**
 * PromptGen 模型返回
 * 继承通用的 InferResponse；目前没有额外字段
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrompResp extends InferResponse {
    // 如后续需要扩展字段，在这里加；先保持空类即可
}