package org.horizon.module.qa.dal.dataobject.useranswers;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 用户回复表（保存终端用户每个提问对应的模型回复） DO
 *
 * @author freedom
 */
@TableName("qa_user_answers")
@KeySequence("qa_user_answers_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswersDO extends BaseDO {

    /**
     * 回复ID
     */
    @TableId
    private Long id;
    /**
     * 注册用户ID（若有登录则填，否则为空）
     */
    private Long userId;
    /**
     * 对应的问题ID（qa_user_questions.id）
     */
    private Long questionsId;
    /**
     * 语言（ja/en/zh/auto），默认ja
     */
    private String langId;
    /**
     * 返回给用户的最终答案
     */
    private String answerText;
    /**
     * 翻译后的文本（终端用户母语）
     */
    private String translatedText;
    /**
     * 使用的模型名称（plamo-7B, mistral 等）
     */
    private String modelName;


}