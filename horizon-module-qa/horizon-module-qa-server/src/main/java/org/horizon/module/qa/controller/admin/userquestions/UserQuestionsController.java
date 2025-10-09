package org.horizon.module.qa.controller.admin.userquestions;

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

import org.horizon.module.qa.controller.admin.userquestions.vo.*;
import org.horizon.module.qa.dal.dataobject.userquestions.UserQuestionsDO;
import org.horizon.module.qa.service.userquestions.UserQuestionsService;

@Tag(name = "管理后台 - RAG 用户问题表（终端用户的每一次提问记录）")
@RestController
@RequestMapping("/qa/user-questions")
@Validated
public class UserQuestionsController {

    @Resource
    private UserQuestionsService userQuestionsService;

    @PostMapping("/create")
    @Operation(summary = "创建RAG 用户问题表（终端用户的每一次提问记录）")
    @PreAuthorize("@ss.hasPermission('qa:user-questions:create')")
    public CommonResult<Long> createUserQuestions(@Valid @RequestBody UserQuestionsSaveReqVO createReqVO) {
        return success(userQuestionsService.createUserQuestions(createReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得RAG 用户问题表（终端用户的每一次提问记录）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('qa:user-questions:query')")
    public CommonResult<UserQuestionsRespVO> getUserQuestions(@RequestParam("id") Long id) {
        UserQuestionsDO userQuestions = userQuestionsService.getUserQuestions(id);
        return success(BeanUtils.toBean(userQuestions, UserQuestionsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得RAG 用户问题表（终端用户的每一次提问记录）分页")
    @PreAuthorize("@ss.hasPermission('qa:user-questions:query')")
    public CommonResult<PageResult<UserQuestionsRespVO>> getUserQuestionsPage(@Valid UserQuestionsPageReqVO pageReqVO) {
        PageResult<UserQuestionsDO> pageResult = userQuestionsService.getUserQuestionsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserQuestionsRespVO.class));
    }


}