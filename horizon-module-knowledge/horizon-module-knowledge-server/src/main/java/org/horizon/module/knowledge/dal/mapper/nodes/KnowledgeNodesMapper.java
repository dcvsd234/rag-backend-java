package org.horizon.module.knowledge.dal.mapper.nodes;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.module.knowledge.dal.dataobject.nodes.KnowledgeNodesDO;

import java.util.List;

/**
 * 知识库节点 Mapper
 *
 * 基础的增删改查
 */
@Mapper
public interface KnowledgeNodesMapper {

    /**
     * 插入
     */
    int insert(KnowledgeNodesDO node);

    /**
     * 根据主键查询
     */
    KnowledgeNodesDO selectById(@Param("id") Long id);

    /**
     * 查询所有节点（可按租户过滤）
     */
    List<KnowledgeNodesDO> selectList(@Param("tenantId") Long tenantId);

    /**
     * 更新
     */
    int update(KnowledgeNodesDO node);

    /**
     * 根据主键删除（物理删除）
     */
    int deleteById(@Param("id") Long id);

    /**
     * 批量删除（物理删除）
     */
    int deleteByIds(@Param("ids") List<Long> ids);
}