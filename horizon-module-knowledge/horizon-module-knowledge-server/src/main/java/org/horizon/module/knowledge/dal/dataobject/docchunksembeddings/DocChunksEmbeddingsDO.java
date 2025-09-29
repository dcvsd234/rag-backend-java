package org.horizon.module.knowledge.dal.dataobject.docchunksembeddings;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 文書向量表（存储文本切片的向量表示） DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_doc_chunks_embeddings")
@KeySequence("knowledge_doc_chunks_embeddings_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocChunksEmbeddingsDO extends BaseDO {

    /**
     * 知识库文档切片向量ID
     */
    @TableId
    private Long id;
    /**
     * 切片ID
     */
    private Long chunkId;
    /**
     * 向量模型名
     */
    private String modelName;
    /**
     * 模型版本号
     */
    private String modelVersion;
    /**
     * 模型提供方
     */
    private String provider;
    /**
     * 隔离域（多租户 + 知识库上下文）
     */
    private String namespace;
    /**
     * 距离度量方式（cosine / L2 / ip）
     */
    private String metric;
    /**
     * 向量维度（固定 2048）
     */
    private Integer dim;
    /**
     * 向量列（pgvector halfvec，2048 维）
     */
    private Object embedding;


}