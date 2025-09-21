package org.horizon.module.knowledge.controller.admin.nodes;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.framework.common.util.object.BeanUtils;
import static org.horizon.framework.common.pojo.CommonResult.success;

import org.horizon.framework.apilog.core.annotation.ApiAccessLog;
import static org.horizon.framework.apilog.core.enums.OperateTypeEnum.*;

import org.horizon.module.knowledge.controller.admin.nodes.vo.*;
import org.horizon.module.knowledge.dal.dataobject.nodes.KnowledgeNodesDO;
import org.horizon.module.knowledge.service.nodes.KnowledgeNodesService;

@Tag(name = "管理后台 - RAG 知识库节点")
@RestController
@RequestMapping("/knowledge/nodes")
@Validated
public class KnowledgeNodesController {

    @Resource
    private KnowledgeNodesService nodesService;

    @PostMapping("/create")
    @Operation(summary = "创建RAG 知识库节点")
    @PreAuthorize("@ss.hasPermission('knowledge:nodes:create')")
    public CommonResult<Long> createNodes(@Valid @RequestBody KnowledgeNodesSaveReqVO createReqVO) {
        return success(nodesService.createNodes(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新RAG 知识库节点")
    @PreAuthorize("@ss.hasPermission('knowledge:nodes:update')")
    public CommonResult<Boolean> updateNodes(@Valid @RequestBody KnowledgeNodesSaveReqVO updateReqVO) {
        nodesService.updateNodes(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除RAG 知识库节点")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('knowledge:nodes:delete')")
    public CommonResult<Boolean> deleteNodes(@RequestParam("id") Long id) {
        nodesService.deleteNodes(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除RAG 知识库节点")
                @PreAuthorize("@ss.hasPermission('knowledge:nodes:delete')")
    public CommonResult<Boolean> deleteNodesList(@RequestParam("ids") List<Long> ids) {
        nodesService.deleteNodesListByIds(ids);
        return success(true);
    }


}