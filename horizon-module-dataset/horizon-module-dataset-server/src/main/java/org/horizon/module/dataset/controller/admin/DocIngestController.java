package org.horizon.module.dataset.controller.admin;

import lombok.RequiredArgsConstructor;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.dataset.api.vo.doc.FileUploadResp;
import org.horizon.module.dataset.api.vo.doc.SubmitDocReq;
import org.horizon.module.dataset.api.vo.doc.UploadStatusResp;
import org.horizon.module.dataset.service.ingest.DocIngestService;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

import static org.horizon.framework.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/admin/dataset/doc")
@RequiredArgsConstructor
public class DocIngestController {

    private final DocIngestService docIngestService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<FileUploadResp> upload(@RequestParam("tenantId") String tenantId,
                                               @RequestParam(value = "leafId", required = false) String leafId,
                                               @RequestPart("file") MultipartFile file) throws Exception {
        return CommonResult.success(docIngestService.upload(tenantId, leafId, file));
    }

    @PostMapping("/submit")
    public CommonResult<UploadStatusResp> submit(@RequestBody SubmitDocReq req) {
        UploadStatusResp resp = docIngestService.submit(req);
        return success(resp);
    }
}