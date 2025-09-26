package org.horizon.module.hotpoint.dal.dataobject;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 关键词 DO
 *
 */
@TableName("hotpoint_keywords")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordDO extends BaseDO {

    /**
     * keyword ID
     */
    private Long id;

    /**
     * keyword_text ID
     */
    private String text;


    /**
     * question ID
     */
    private Long tenantId;

}