package org.horizon.module.trends.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.trends.controller.admin.vo.questionKeyword.QuestionKeywordSaveReqVO;
import org.horizon.module.trends.service.QuestionKeywordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.horizon.framework.common.pojo.CommonResult.success;

@Tag(name = "admin - trends")
@RestController
@RequestMapping("/system/trends")
@Validated
public class QuestionKeywordController {

    @Resource
    private QuestionKeywordService questionKeywordService;

    @PostMapping("/createQuestionKeyword")
    @Operation(summary = "创建问题关联的关键词")
    @PreAuthorize("@ss.hasPermission('trends:question-keyword:create')")
    public CommonResult<Long> create(@Valid @RequestBody QuestionKeywordSaveReqVO createReqVO) {
        return success(questionKeywordService.create(createReqVO));
    }
}