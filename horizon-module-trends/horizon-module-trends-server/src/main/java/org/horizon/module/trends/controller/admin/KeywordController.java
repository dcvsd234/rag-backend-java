package org.horizon.module.trends.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.trends.controller.admin.vo.keyword.KeywordSaveReqVO;
import org.horizon.module.trends.controller.admin.vo.keyword.KeywordPageReqVO;
import org.horizon.module.trends.controller.admin.vo.keyword.KeywordRespVO;
import org.horizon.module.trends.dal.dataobject.KeywordDO;
import org.horizon.module.trends.service.KeywordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.horizon.framework.common.util.object.BeanUtils;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.horizon.framework.common.pojo.CommonResult.success;

@Tag(name = "admin - trends")
@RestController
@RequestMapping("/trends/keyword")
@Validated
public class KeywordController {

    @Resource
    private KeywordService keywordService;

    @GetMapping("/page")
    @Operation(summary = "获取关键词分页列表")
    @PreAuthorize("@ss.hasPermission('trends:keyword:query')")
    public CommonResult<PageResult<KeywordRespVO>> getKeywordsPage(@Validated KeywordPageReqVO pageReqVO) {
        PageResult<KeywordDO> pageResult = keywordService.selectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, KeywordRespVO.class));
    }
}