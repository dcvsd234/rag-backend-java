package org.horizon.framework.ai.core.dto.prompt;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.horizon.framework.ai.core.dto.base.BaseReq;

import java.util.List;
import java.util.Map;

/**
 * FAQ → Prompt 输入数据结构
 * - 必须包含 FAQ 三要素（question, answer, categories）
 * - 其他字段为可选控制项
 *
 * 对应 Python: PromptRequest(BaseRequest)
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromptReq extends BaseReq {

    // —— FAQ 三要素（必填） ——
    private String pipelineName;          // pipeline 名称，如 "policy"
    private String question;              // FAQ 的提问
    private String answer;                // FAQ 的回答
    private List<String> categories;      // FAQ 的分类词（至少一个）

    // —— 可选：受众 & 模板 ——
    private String audience;              // 目标受众，如 "外国人"
    private String template;              // 自定义提示词模板
    private Map<String, Object> templateVars; // 模板变量（覆盖自动注入的 question/answer/categories）

    // —— 控制参数（覆盖默认值） ——
    private Integer maxTokens;            // 默认 300
    private Double temperature;           // 默认 0.35
    private Double topP;                  // 默认 0.9
    private Integer topK;                 // 默认 40
    private Double repeatPenalty;         // 默认 1.05
    private List<String> stop;            // 默认 []
    private Integer seed;                 // 默认 null
    private Map<String, Object> genParams; // 整体覆盖采样参数，优先级最高
}