package org.horizon.module.knowledge.dal.dataobject.faqcategorylinks;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * FAQ分類关联 DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_faq_category_links")
@KeySequence("knowledge_faq_category_links_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqCategoryLinksDO extends BaseDO {

    /**
     * FAQ分类关联表ID
     */
    @TableId
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * FAQ ID（knowledge_faqs.id）
     */
    private Long faqId;
    /**
     * 分類ID（knowledge_categories.id）
     */
    private Long categoryId;
    /**
     * 关联强度（人工=1.00，模型<1.0）
     */
    private Double confidence;
    /**
     * 关联来源（manual / rule / model）
     */
    private String linkSource;


}
