package org.horizon.module.hotpoint.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;



/**
 * 问题记录表 DO
 *
 */
@TableName("hotpoint_questions")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDO extends BaseDO {

    /**
     * question ID
     */
    private Long id;

    /**
     * user ID
     */
    private Long user_id;


    /**
     * question text
     */
    private String text;


    /**
     * question normalized text
     */
    private String normalized_text;

    /**
     * answer text
     */
    private String answer_text;

    /**
     * create time
     */
    private String created_at;

    /**
     * updated time
     */
    private String updated_at;

    /**
     * ask count
     */
    private String ask_count;

    /**
     * ask count
     */
    private String tenant_id;
}