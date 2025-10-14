package org.horizon.framework.ai.core.dto.infer;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有模型通用的基础响应结构。
 * 可被翻译 / 生成 / 检索等响应继承并复用。
 *
 * 对应 Python:
 * class BaseResponse(BaseModel):
 *     text: Optional[str]
 *     model: Optional[str]
 *     runtime_ms: Optional[int]
 *     metadata: Dict[str, Any]
 *     class Config: extra = "allow"
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InferResponse {

    /** 主要输出文本（如翻译、生成结果等） */
    private String text;

    /** 实际使用的模型名称 */
    private String model;

    /** 推理耗时（毫秒） */
    @JsonProperty("runtime_ms")
    private Integer runtimeMs;

    /** 附加元信息（如 trace_id、语言等） */
    private Map<String, Object> metadata = new HashMap<>();

    /**
     * 额外字段，等价于 Python Pydantic Config(extra="allow")
     * 可以捕获 score、results 等未在类里定义的字段
     */
    @JsonIgnore
    @Builder.Default
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        additionalProperties.put(name, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}