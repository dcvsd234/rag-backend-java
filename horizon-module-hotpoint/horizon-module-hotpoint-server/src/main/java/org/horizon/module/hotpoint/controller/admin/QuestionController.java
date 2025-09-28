package org.horizon.module.hotpoint.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.hotpoint.controller.admin.vo.question.QuestionSaveReqVO;
import org.horizon.module.hotpoint.service.QuestionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.horizon.framework.common.pojo.CommonResult.success;


import org.horizon.module.knowledge.controller.admin.nodes.vo.*;

@Tag(name = "admin - hotpoint")
@RestController
@RequestMapping("/hotpoint/question")
@Validated
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @PostMapping("/create")
    @Operation(summary = "创建问题")
    @PreAuthorize("@ss.hasPermission('hotpoint:question:create')")
    public CommonResult<Long> createQuestion(@Valid @RequestBody QuestionSaveReqVO createReqVO) {
        return success(questionService.create(createReqVO));
    }
}