package org.horizon.module.hotpoint.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.hotpoint.controller.admin.vo.keyword.KeywordSaveReqVO;
import org.horizon.module.hotpoint.controller.admin.vo.keyword.KeywordPageReqVO;
import org.horizon.module.hotpoint.controller.admin.vo.keyword.KeywordRespVO;
import org.horizon.module.hotpoint.dal.dataobject.KeywordDO;
import org.horizon.module.hotpoint.service.KeywordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.horizon.framework.common.util.object.BeanUtils;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.horizon.framework.common.pojo.CommonResult.success;

@Tag(name = "admin - hotpoint")
@RestController
@RequestMapping("/hotpoint/keyword")
@Validated
public class KeywordController {

    @Resource
    private KeywordService keywordService;

    @PostMapping("/create")
    @Operation(summary = "创建关键词")
    @PreAuthorize("@ss.hasPermission('hotpoint:keyword:create')")
    public CommonResult<Long> create(@Valid @RequestBody KeywordSaveReqVO createReqVO) {
        return success(keywordService.create(createReqVO));
    }

    @GetMapping("/page")
    @Operation(summary = "获取关键词分页列表")
    @PreAuthorize("@ss.hasPermission('hotpoint:keyword:query')")
    public CommonResult<PageResult<KeywordRespVO>> getKeywordsPage(@Validated KeywordPageReqVO pageReqVO) {
        PageResult<KeywordDO> pageResult = keywordService.selectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, KeywordRespVO.class));
    }

}