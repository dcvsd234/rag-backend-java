package org.horizon.module.knowledge.controller.admin.document;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.framework.common.util.object.BeanUtils;
import org.horizon.module.knowledge.api.doc.dto.SubmitDocReqDTO;
import org.horizon.module.knowledge.controller.admin.document.vo.SubmitDocReqVO;
import org.horizon.module.knowledge.service.document.DocumentDatasetService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台 - 文档数据集")
@RestController
@RequestMapping("/admin/knowledge/doc")
@RequiredArgsConstructor
@Validated
public class DocumentDatasetController {

    private final DocumentDatasetService documentDatasetService;

    @PostMapping("/submit")
    @Operation(summary = "提交文档 + FAQ")
    public CommonResult<Void> submit(@RequestBody @Validated SubmitDocReqVO reqVO) {
        SubmitDocReqDTO reqDTO = BeanUtils.toBean(reqVO,SubmitDocReqDTO.class);
        // 调用 Service
        CommonResult result = documentDatasetService.submit(reqDTO);
        // DTO -> VO
        return result;
    }
}