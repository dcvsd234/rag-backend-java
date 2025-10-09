package org.horizon.module.knowledge.controller.admin.document.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 文档 + FAQ 提交 Request VO")
@Data
public class SubmitDocReqVO {

    @Schema(description = "文档数据库 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty
    private String infraFileId;

    @Schema(description = "存储 Key（MinIO 中的路径）", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1/leaf-labor/20250913/test_1757757130425.md")
    @NotEmpty
    private String key;

    @Schema(description = "FAQ 列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid
    @NotNull
    private List<FaqVO> faqs;
}