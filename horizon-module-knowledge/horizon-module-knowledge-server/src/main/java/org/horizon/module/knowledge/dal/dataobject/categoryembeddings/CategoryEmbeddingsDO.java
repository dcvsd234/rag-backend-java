package org.horizon.module.knowledge.dal.dataobject.categoryembeddings;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG FAQ 分类向量 DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_category_embeddings")
@KeySequence("knowledge_category_embeddings_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEmbeddingsDO extends BaseDO {

    /**
     * 分类向量ID
     */
    @TableId
    private Long id;
    /**
     * FAQ 分类ID (FK → knowledge_categories.id)
     */
    private Long categoryId;
    /**
     * 向量模型名
     */
    private String modelName;
    /**
     * 模型版本
     */
    private String modelVersion;
    /**
     * 提供方（ELYZA/voyage/local等）
     */
    private String provider;
    /**
     * 隔离域 (例: tenant:1|kb:category)
     */
    private String namespace;
    /**
     * 距离度量（cosine/L2/ip）
     */
    private String metric;
    /**
     * 向量维度 (固定 2048)
     */
    private Integer dim;
    /**
     * 向量列 (pgvector halfvec, 2048维)
     */
    private Object embedding;


}
