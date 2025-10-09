package org.horizon.module.knowledge.dal.dataobject.faqembeddings;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * FAQ 向量テーブル（FAQ 文本のベクトル表現を保存） DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_faq_embeddings")
@KeySequence("knowledge_faq_embeddings_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqEmbeddingsDO extends BaseDO {

    /**
     * FAQ向量ID
     */
    @TableId
    private Long id;
    /**
     * FAQ ID（knowledge_faqs.id への参照を推奨）
     */
    private Long faqId;
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
     * 隔离域（テナント／FAQ）
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
     * 問答区分
     */
    private Integer qaRole;
    /**
     * 向量列（pgvector halfvec，2048 维）
     */
    private Object embedding;


}
