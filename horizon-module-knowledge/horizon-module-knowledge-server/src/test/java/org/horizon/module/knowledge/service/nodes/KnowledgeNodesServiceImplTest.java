package org.horizon.module.knowledge.service.nodes;

import org.horizon.framework.test.core.ut.BaseDbUnitTest;
import org.horizon.module.knowledge.controller.admin.nodes.vo.KnowledgeNodesSaveReqVO;
import org.horizon.module.knowledge.dal.dataobject.nodes.KnowledgeNodesDO;
import org.horizon.module.knowledge.dal.dataobject.nodes.KnowledgeNodesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

import static org.horizon.framework.test.core.util.AssertUtils.assertServiceException;
import static org.horizon.framework.test.core.util.RandomUtils.*;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.NODES_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link KnowledgeNodesServiceImpl} 的单元测试类
 */
@Import(KnowledgeNodesServiceImpl.class)
public class KnowledgeNodesServiceImplTest extends BaseDbUnitTest {

    @Resource
    private KnowledgeNodesServiceImpl knowledgeNodesService;

    @Resource
    private KnowledgeNodesMapper knowledgeNodesMapper;

    @Test
    public void testCreateNodes_success() {
        // 准备参数：创建时不允许带 id；避免随机 parentId 撞自引用外键
        KnowledgeNodesSaveReqVO reqVO = randomPojo(KnowledgeNodesSaveReqVO.class, o -> {
            o.setId(null);
            o.setParentId(null);
        });

        // 调用
        Long nodeId = knowledgeNodesService.createNodes(reqVO);
        assertNotNull(nodeId);

        // 校验记录（只校验稳定字段，避免与 DB 默认值/时间戳冲突）
        KnowledgeNodesDO dbNode = knowledgeNodesMapper.selectById(nodeId);
        assertNotNull(dbNode);
        assertEquals(reqVO.getTenantId(), dbNode.getTenantId());
        assertEquals(reqVO.getName(), dbNode.getName());
        assertEquals(reqVO.getRemark(), dbNode.getRemark());
        assertNull(dbNode.getParentId());
    }

    @Test
    public void testUpdateNodes_success() {
        // 先插一条待更新数据：显式置空 id/parentId，避免外键
        KnowledgeNodesDO dbNode = randomPojo(KnowledgeNodesDO.class, o -> {
            o.setId(null);
            o.setParentId(null);
        });
        knowledgeNodesMapper.insert(dbNode);

        // 准备参数：指定要更新的 id；保持 parentId 合法（这里为 null）
        KnowledgeNodesSaveReqVO reqVO = randomPojo(KnowledgeNodesSaveReqVO.class, o -> {
            o.setId(dbNode.getId());
            o.setParentId(null);
            o.setName(randomString());
        });

        // 调用
        knowledgeNodesService.updateNodes(reqVO);

        // 校验
        KnowledgeNodesDO updated = knowledgeNodesMapper.selectById(dbNode.getId());
        assertNotNull(updated);
        assertEquals(reqVO.getTenantId(), updated.getTenantId());
        assertEquals(reqVO.getName(), updated.getName());
        assertEquals(reqVO.getRemark(), updated.getRemark());
        assertNull(updated.getParentId());
    }

    @Test
    public void testUpdateNodes_notExists() {
        // 准备参数：明确一个不存在的 id
        KnowledgeNodesSaveReqVO reqVO = randomPojo(KnowledgeNodesSaveReqVO.class, o -> {
            o.setId(randomLongId());
            o.setParentId(null);
        });

        // 调用并断言
        assertServiceException(() -> knowledgeNodesService.updateNodes(reqVO), NODES_NOT_EXISTS);
    }

    @Test
    public void testDeleteNodes_success() {
        // 先插入一条可删除的数据
        KnowledgeNodesDO dbNode = randomPojo(KnowledgeNodesDO.class, o -> {
            o.setId(null);
            o.setParentId(null);
        });
        knowledgeNodesMapper.insert(dbNode);

        // 调用
        knowledgeNodesService.deleteNodes(dbNode.getId());

        // 断言
        assertNull(knowledgeNodesMapper.selectById(dbNode.getId()));
    }

    @Test
    public void testDeleteNodes_notExists() {
        Long id = randomLongId();
        assertServiceException(() -> knowledgeNodesService.deleteNodes(id), NODES_NOT_EXISTS);
    }

    @Test
    public void testGetNodes_success() {
        // 先插入一条数据
        KnowledgeNodesDO dbNode = randomPojo(KnowledgeNodesDO.class, o -> {
            o.setId(null);
            o.setParentId(null);
        });
        knowledgeNodesMapper.insert(dbNode);

        // 调用
        KnowledgeNodesDO node = knowledgeNodesService.getNodes(dbNode.getId());

        // 校验（只校验稳定字段）
        assertNotNull(node);
        assertEquals(dbNode.getId(), node.getId());
        assertEquals(dbNode.getTenantId(), node.getTenantId());
        assertEquals(dbNode.getName(), node.getName());
        assertEquals(dbNode.getRemark(), node.getRemark());
        assertNull(node.getParentId());
    }
}