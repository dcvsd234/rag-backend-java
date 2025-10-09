package org.horizon.module.knowledge.dal.dataobject.docs;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 知識庫文書 DO
 *
 * @author freedom
 */
@TableName("knowledge_docs")
@KeySequence("knowledge_docs_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocsDO extends BaseDO {

    /**
     * 知识库文档ID
     */
    @TableId
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 知识库节点
     */
    private Long nodeId;
    /**
     * FK → knowledge_doc_versions.id
     */
    private Long activeVersionId;
    /**
     * 贡献者名字
     */
    private String attributionName;
    /**
     * pending、public、internal、private、archived
     */
    private Integer displayFlag;
    /**
     * 備考
     */
    private String remark;


}