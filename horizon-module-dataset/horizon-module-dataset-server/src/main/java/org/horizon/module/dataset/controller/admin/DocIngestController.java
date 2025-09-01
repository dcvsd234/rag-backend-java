package org.horizon.module.dataset.controller.admin;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.dataset.api.vo.doc.UploadStatusResp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.horizon.framework.common.pojo.CommonResult.success;

/**
 * User App - Document ingestion (submit documents, Q&A evidence, etc.)
 */
@Tag(name = "User App - Document Ingestion")
@RestController
@RequestMapping("/app/dataset/doc")
@Validated
@RequiredArgsConstructor
public class DocIngestController {

    // TODO: 注入 service，比如 private final DocIngestService docIngestService;

    @PostMapping("/submit")
    @Operation(summary = "Submit document and evidence")
    public CommonResult<UploadStatusResp> submitDoc(
            @RequestParam("tenantId") String tenantId,
            @RequestParam("fileName") String fileName,
            @RequestParam(value = "question", required = false) String question,
            @RequestParam(value = "prompt", required = false) String prompt,
            @RequestParam(value = "evidence", required = false) String[] evidence
    ) {
        // TODO: 调用 service 处理上传逻辑
        UploadStatusResp resp = UploadStatusResp.builder()
                .status("SUCCESS")
                .docId("doc-" + System.currentTimeMillis())
                .fileName(fileName)
                .fileSize(12345L)
                .build();

        return success(resp);
    }

    @GetMapping("/status/{docId}")
    @Operation(summary = "Get upload status")
    public CommonResult<UploadStatusResp> getStatus(@PathVariable("docId") String docId) {
        // TODO: 从数据库或缓存里查状态
        UploadStatusResp resp = UploadStatusResp.builder()
                .status("PROCESSING")
                .docId(docId)
                .fileName("unknown.pdf")
                .fileSize(0L)
                .build();

        return success(resp);
    }
}