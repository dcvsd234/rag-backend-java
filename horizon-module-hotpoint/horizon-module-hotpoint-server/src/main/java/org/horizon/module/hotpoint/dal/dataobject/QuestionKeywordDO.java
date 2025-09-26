package org.horizon.module.hotpoint.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;


/**
 * 问题-关键词记录表 DO
 *
 */
@TableName("hotpoint_question_keywords")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionKeywordDO extends BaseDO {

    /**
     * keyword ID
     */
    private Long id;

    /**
     * question ID
     */
    private Long question_id;


    /**
     * keyword ID
     */
    private Long keyword_id;

}