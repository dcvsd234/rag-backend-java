package org.horizon.module.knowledge.dal.dataobject.categories;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG FAQ 分类 DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_categories")
@KeySequence("knowledge_categories_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesDO extends BaseDO {

    /**
     * FAQ分类ID
     */
    @TableId
    private Long id;

    /**
     * 分类名称
     */
    private String question;
    /**
     * 语言ID（如 ja / en）
     */
    private String langId;
    /**
     * 显示标志 (pending/public/internal/private/archived)
     */
    private String displayFlag;

    /**
     * FAQID
     */
    private Long faqId;


}
