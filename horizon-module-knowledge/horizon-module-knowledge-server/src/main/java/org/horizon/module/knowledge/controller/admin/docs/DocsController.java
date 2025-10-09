package org.horizon.module.knowledge.controller.admin.docs;

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

import org.horizon.module.knowledge.controller.admin.docs.vo.*;
import org.horizon.module.knowledge.dal.dataobject.docs.DocsDO;
import org.horizon.module.knowledge.service.docs.DocsService;

@Tag(name = "管理后台 - RAG 知識庫文書")
@RestController
@RequestMapping("/knowledge/docs")
@Validated
public class DocsController {

    @Resource
    private DocsService docsService;

    @PostMapping("/create")
    @Operation(summary = "创建RAG 知識庫文書")
    @PreAuthorize("@ss.hasPermission('knowledge:docs:create')")
    public CommonResult<Long> createDocs(@Valid @RequestBody DocsSaveReqVO createReqVO) {
        return success(docsService.createDocs(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新RAG 知識庫文書")
    @PreAuthorize("@ss.hasPermission('knowledge:docs:update')")
    public CommonResult<Boolean> updateDocs(@Valid @RequestBody DocsSaveReqVO updateReqVO) {
        docsService.updateDocs(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除RAG 知識庫文書")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('knowledge:docs:delete')")
    public CommonResult<Boolean> deleteDocs(@RequestParam("id") Long id) {
        docsService.deleteDocs(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除RAG 知識庫文書")
    @PreAuthorize("@ss.hasPermission('knowledge:docs:delete')")
    public CommonResult<Boolean> deleteDocsList(@RequestParam("ids") List<Long> ids) {
        docsService.deleteDocsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得RAG 知識庫文書")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('knowledge:docs:query')")
    public CommonResult<DocsRespVO> getDocs(@RequestParam("id") Long id) {
        DocsDO docs = docsService.getDocs(id);
        return success(BeanUtils.toBean(docs, DocsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得RAG 知識庫文書分页")
    @PreAuthorize("@ss.hasPermission('knowledge:docs:query')")
    public CommonResult<PageResult<DocsRespVO>> getDocsPage(@Valid DocsPageReqVO pageReqVO) {
        PageResult<DocsDO> pageResult = docsService.getDocsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DocsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出RAG 知識庫文書 Excel")
    @PreAuthorize("@ss.hasPermission('knowledge:docs:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDocsExcel(@Valid DocsPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DocsDO> list = docsService.getDocsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "RAG 知識庫文書.xls", "数据", DocsRespVO.class,
                BeanUtils.toBean(list, DocsRespVO.class));
    }

}