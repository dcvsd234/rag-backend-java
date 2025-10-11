package org.horizon.module.trends.dal.dataobject;

import lombok.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 关键词 DO
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordDO extends BaseDO {

    /**
     *  ID
     */
    private Long id;

    /**
     * text ID
     */
    private String text;


    /**
     * tenant ID
     */
    private Long tenantId;

}