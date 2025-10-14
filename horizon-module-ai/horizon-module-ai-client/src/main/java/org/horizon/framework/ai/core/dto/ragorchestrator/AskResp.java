package org.horizon.framework.ai.core.dto.ragorchestrator;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.horizon.framework.ai.core.dto.infer.InferResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 多语言问答返回结构（完整版）
 * <p>
 * 对应 Python: AskResponse (继承 BaseResponse)
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AskResp extends InferResponse {

    /** 用户输入的原始问题（母语） */
    private String originalQuestion;

    /** 用户输入统一翻译为日语后的内容 */
    private String translatedInput;

    /** 模型在日语下生成的原始回答 */
    private String generatedText;

    /** 翻译回用户母语的最终回答 */
    private String finalText;

    /** 回答所用到的来源列表（类型 + ID 即可） */
    @Builder.Default
    private List<SourceItem> sources = new ArrayList<>();
}