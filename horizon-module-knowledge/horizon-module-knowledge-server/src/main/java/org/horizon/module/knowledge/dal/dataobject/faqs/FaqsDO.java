package org.horizon.module.knowledge.dal.dataobject.faqs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

import javax.validation.constraints.NotEmpty;

/**
 * RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点） DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_faqs")
@KeySequence("knowledge_faqs_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqsDO extends BaseDO {

    /**
     * FAQ主表ID
     */
    @TableId
    private Long id;

    /**
     * 租户ID
     */
    @TableId
    private Long tenantId;
    /**
     * 知識庫ノードID（knowledge_nodes.id）
     */
    private Long nodeId;
    /**
     * 知識庫文档版本ID（knowledge_doc_versions.id）
     */
    private Long knowledgeDocVersionsId;
    /**
     * FAQ来源（manual/doc/import/auto）
     */
    private String sourceType;
    /**
     * ユーザー提问
     */
    private String question;
    /**
     * 回答内容
     */
    private String answer;
    /**
     * 言語ID (例: ja, en)
     */
    private String langId;
    /**
     * 表示状態（pending/public/internal/private/archived）
     */
    private String displayFlag;

    @Schema(description = "分类词（多个）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private List<String> categories;

}