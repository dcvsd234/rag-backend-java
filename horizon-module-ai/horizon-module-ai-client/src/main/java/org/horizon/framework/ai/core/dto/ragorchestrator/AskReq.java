package org.horizon.framework.ai.core.dto.ragorchestrator;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.horizon.framework.ai.core.dto.base.BaseReq;

/**
 * 多语言问答请求结构（RAG Orchestrator 使用）
 * <p>
 * 继承自 BaseReq，可统一包含 tenantId、traceId、langId 等公共字段。
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AskReq extends BaseReq {

    /** 用户提出的问题 */
    private String question;

    /** 会话标识，用于多轮对话追踪 */
    private String sessionId;
}