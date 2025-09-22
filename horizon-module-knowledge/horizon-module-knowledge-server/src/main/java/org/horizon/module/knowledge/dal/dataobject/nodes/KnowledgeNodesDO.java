package org.horizon.module.knowledge.dal.dataobject.nodes;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 知识库节点 DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_nodes")
@KeySequence("knowledge_nodes_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeNodesDO extends BaseDO {

    /**
     * 知识库节点ID (主键，自增)
     */
    private Long id;

    /**
     * 租户ID（多租户隔离）
     */
    private Long tenantId;

    /**
     * 节点名称（展示给用户，如“劳动法”“移民法”）
     */
    private String name;

    /**
     * 父节点ID，空表示根节点
     */
    private Long parentId;

    /**
     * 备注
     */
    private String remark;


}