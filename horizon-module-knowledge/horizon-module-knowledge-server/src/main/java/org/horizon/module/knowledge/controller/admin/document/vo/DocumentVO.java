package org.horizon.module.knowledge.controller.admin.document.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Schema(description = "管理后台 - 文档信息 VO")
@Data
public class DocumentVO {

    @Schema(description = "文件 ID（上传接口返回的 fileId）", requiredMode = Schema.RequiredMode.REQUIRED, example = "file-123")
    @NotEmpty(message = "文件 ID 不能为空")
    private String id;

    @Schema(description = "文件名", requiredMode = Schema.RequiredMode.REQUIRED, example = "政策解读.pdf")
    @NotEmpty(message = "文件名不能为空")
    private String fileName;

    @Schema(description = "文件类型", example = "application/pdf")
    private String mimeType;

    @Schema(description = "字数", example = "2048")
    private Integer length;

    @Schema(description = "页数", example = "12")
    private Integer pagesCount;
}