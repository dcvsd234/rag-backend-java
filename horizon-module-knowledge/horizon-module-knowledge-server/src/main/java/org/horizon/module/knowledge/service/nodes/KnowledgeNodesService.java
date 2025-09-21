package org.horizon.module.knowledge.service.nodes;

import org.horizon.module.knowledge.controller.admin.nodes.vo.KnowledgeNodesSaveReqVO;
import org.horizon.module.knowledge.dal.dataobject.nodes.KnowledgeNodesDO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * RAG 知识库节点 Service 接口
 */
public interface KnowledgeNodesService {

    /**
     * 创建 RAG 知识库节点
     * @param createReqVO 创建信息
     * @return 节点 ID
     */
    Long createNodes(@Valid @NotNull KnowledgeNodesSaveReqVO createReqVO);

    /**
     * 更新 RAG 知识库节点
     * @param updateReqVO 更新信息（必须包含 id）
     */
    void updateNodes(@Valid @NotNull KnowledgeNodesSaveReqVO updateReqVO);

    /**
     * 删除 RAG 知识库节点（物理删除）
     * @param id 节点 ID
     */
    void deleteNodes(@NotNull Long id);

    /**
     * 批量删除 RAG 知识库节点（物理删除）
     * @param ids 节点 ID 集合
     */
    void deleteNodesListByIds(@NotEmpty List<@NotNull Long> ids);

    /**
     * 根据 ID 获取单个 RAG 知识库节点
     * @param id 节点 ID
     * @return 节点实体
     */
    KnowledgeNodesDO getNodes(@NotNull Long id);

    /**
     * 获取指定租户的所有 RAG 知识库节点
     * @param tenantId 租户 ID，可为空（为空则查全量）
     * @return 节点列表
     */
    List<KnowledgeNodesDO> getNodesList(Long tenantId);
}