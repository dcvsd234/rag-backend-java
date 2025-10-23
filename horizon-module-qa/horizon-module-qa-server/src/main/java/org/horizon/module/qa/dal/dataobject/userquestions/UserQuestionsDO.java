package org.horizon.module.qa.dal.dataobject.userquestions;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 用户问题表（终端用户的每一次提问记录） DO
 *
 * 对应表：qa_user_questions
 */
@TableName("qa_user_questions")
@KeySequence("qa_user_questions_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionsDO extends BaseDO {

    /**
     * 问题ID
     */
    @TableId
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 注册用户ID（若有登录则填，否则为空）
     */
    private Long userId;

    /**
     * 匿名用户ID（未注册用户使用） — PostgreSQL uuid 类型
     */
    private String anonUserId;

    /**
     * 会话标识（用于多轮对话追踪；前端携带）
     */
    private String sessionId;

    /**
     * 语言（ja/en/zh/auto），默认ja
     */
    private String langId;

    /**
     * 用户原始提问
     */
    private String originalText;

    /**
     * 翻译后的文本（例如统一为日语以便检索）
     */
    private String translatedText;

}