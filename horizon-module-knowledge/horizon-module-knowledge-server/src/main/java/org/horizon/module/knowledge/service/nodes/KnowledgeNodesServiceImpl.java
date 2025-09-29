package org.horizon.module.knowledge.service.nodes;

import org.horizon.framework.common.util.object.BeanUtils;
import org.horizon.module.knowledge.dal.mapper.nodes.KnowledgeNodesMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import java.util.List;

import org.horizon.module.knowledge.controller.admin.nodes.vo.KnowledgeNodesSaveReqVO;
import org.horizon.module.knowledge.dal.dataobject.nodes.KnowledgeNodesDO;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;

import static org.horizon.module.knowledge.enums.ErrorCodeConstants.NODES_NOT_EXISTS;

/**
 * RAG 知识库节点 Service 实现类
 *
 * @author 芋
 */
@Service
@Validated
public class KnowledgeNodesServiceImpl implements KnowledgeNodesService {

    @Resource
    private KnowledgeNodesMapper nodesMapper;

    @Override
    public Long createNodes(KnowledgeNodesSaveReqVO createReqVO) {
        KnowledgeNodesDO nodes = BeanUtils.toBean(createReqVO, KnowledgeNodesDO.class);
        nodesMapper.insert(nodes);
        return nodes.getId();
    }

    @Override
    public void updateNodes(KnowledgeNodesSaveReqVO updateReqVO) {
        validateNodesExists(updateReqVO.getId());
        KnowledgeNodesDO updateObj = BeanUtils.toBean(updateReqVO, KnowledgeNodesDO.class);
        nodesMapper.update(updateObj); // ✅ 改为你的 Mapper 的 update 方法
    }

    @Override
    public void deleteNodes(Long id) {
        validateNodesExists(id);
        nodesMapper.deleteById(id); // ✅ 用自定义 deleteById
    }

    @Override
    public void deleteNodesListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        nodesMapper.deleteByIds(ids); // ✅ 改为你的 Mapper 的 deleteByIds
    }

    private void validateNodesExists(Long id) {
        if (nodesMapper.selectById(id) == null) {
            throw exception(NODES_NOT_EXISTS);
        }
    }

    @Override
    public KnowledgeNodesDO getNodes(Long id) {
        return nodesMapper.selectById(id);
    }

    @Override
    public List<KnowledgeNodesDO> getNodesList(Long tenantId) {
        return nodesMapper.selectList(tenantId); // ✅ 对应 Mapper 的 selectList
    }
}