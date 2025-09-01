package org.horizon.module.dataset.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.horizon.framework.common.pojo.CommonResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User App - QA Save
 * 用户点击“保存”后，将 QA 和证据句持久化到数据库
 */
@Tag(name = "User App - QA Save")
@RestController
@RequestMapping("/app/dataset/qa")
@Validated
@RequiredArgsConstructor
public class QaSaveController {

    // private final ModelToolsClient modelToolsClient;  // 调用 AI 模型的客户端
    // private final QaSaveService qaSaveService;        // 保存 QA 的服务

    @PostMapping("/save")
    @Operation(summary = "Save QA with evidences into database")
    public CommonResult<Long> save(
            @RequestParam("tenantId") String tenantId,
            @RequestParam("question") String question,
            @RequestParam("prompt") String prompt,
            @RequestParam(value = "evidences", required = false) List<String> evidences
    ) {
        // 1) 调用 AI 生成答案
        // TODO: 构建 GenerateReq
        // TODO: 调用 modelToolsClient.generate(req)
        // TODO: 获取返回结果

        // 2) 保存到数据库
        // TODO: 调用 qaSaveService.saveQa(tenantId, question, prompt, evidences, aiAnswer)
        Long qaId = 1L; // placeholder

        return CommonResult.success(qaId);
    }
}