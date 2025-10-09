package org.horizon.framework.ai.core.dto.ragorchestrator;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.horizon.framework.ai.core.dto.infer.InferResponse;

/**
 * 数据来源条目（最小化设计）
 * <p>
 * 对应 Python: SourceItem (继承 BaseResponse)
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceItem extends InferResponse {

    /** 来源类型（如 doc, faq, chat 等） */
    private String sourceType;

    /** 来源唯一 ID（文档 ID / FAQ ID / 会话 turn ID） */
    private String sourceId;
}