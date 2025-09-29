package org.horizon.module.knowledge.controller.admin.docversions;

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

import org.horizon.framework.excel.core.util.ExcelUtils;

import org.horizon.framework.apilog.core.annotation.ApiAccessLog;
import static org.horizon.framework.apilog.core.enums.OperateTypeEnum.*;

import org.horizon.module.knowledge.controller.admin.docversions.vo.*;
import org.horizon.module.knowledge.dal.dataobject.docversions.DocVersionsDO;
import org.horizon.module.knowledge.service.docversions.DocVersionsService;

@Tag(name = "管理后台 - RAG 知識庫文書バージョン")
@RestController
@RequestMapping("/knowledge/doc-versions")
@Validated
public class DocVersionsController {

    @Resource
    private DocVersionsService docVersionsService;

    @PostMapping("/create")
    @Operation(summary = "创建RAG 知識庫文書バージョン")
    @PreAuthorize("@ss.hasPermission('knowledge:doc-versions:create')")
    public CommonResult<Long> createDocVersions(@Valid @RequestBody DocVersionsSaveReqVO createReqVO) {
        return success(docVersionsService.createDocVersions(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新RAG 知識庫文書バージョン")
    @PreAuthorize("@ss.hasPermission('knowledge:doc-versions:update')")
    public CommonResult<Boolean> updateDocVersions(@Valid @RequestBody DocVersionsSaveReqVO updateReqVO) {
        docVersionsService.updateDocVersions(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除RAG 知識庫文書バージョン")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('knowledge:doc-versions:delete')")
    public CommonResult<Boolean> deleteDocVersions(@RequestParam("id") Long id) {
        docVersionsService.deleteDocVersions(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除RAG 知識庫文書バージョン")
    @PreAuthorize("@ss.hasPermission('knowledge:doc-versions:delete')")
    public CommonResult<Boolean> deleteDocVersionsList(@RequestParam("ids") List<Long> ids) {
        docVersionsService.deleteDocVersionsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得RAG 知識庫文書バージョン")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('knowledge:doc-versions:query')")
    public CommonResult<DocVersionsRespVO> getDocVersions(@RequestParam("id") Long id) {
        DocVersionsDO docVersions = docVersionsService.getDocVersions(id);
        return success(BeanUtils.toBean(docVersions, DocVersionsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得RAG 知識庫文書バージョン分页")
    @PreAuthorize("@ss.hasPermission('knowledge:doc-versions:query')")
    public CommonResult<PageResult<DocVersionsRespVO>> getDocVersionsPage(@Valid DocVersionsPageReqVO pageReqVO) {
        PageResult<DocVersionsDO> pageResult = docVersionsService.getDocVersionsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DocVersionsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出RAG 知識庫文書バージョン Excel")
    @PreAuthorize("@ss.hasPermission('knowledge:doc-versions:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDocVersionsExcel(@Valid DocVersionsPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DocVersionsDO> list = docVersionsService.getDocVersionsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "RAG 知識庫文書バージョン.xls", "数据", DocVersionsRespVO.class,
                BeanUtils.toBean(list, DocVersionsRespVO.class));
    }

}