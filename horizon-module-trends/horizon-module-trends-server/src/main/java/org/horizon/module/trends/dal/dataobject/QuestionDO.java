package org.horizon.module.trends.dal.dataobject;

import lombok.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;



/**
 * 问题记录表 DO
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDO extends BaseDO {

    /**
     *  ID
     */
    private Long id;

    /**
     * user ID
     */
    private Long user_id;


    /**
     * question text
     */
    private String question_text;


    /**
     * question normalized text
     */
    private String normalized_text;

    /**
     * answer text
     */
    private String answer_text;

    /**
     * ask count
     */
    private String ask_count;

    /**
     * ask count
     */
    private String tenant_id;
}