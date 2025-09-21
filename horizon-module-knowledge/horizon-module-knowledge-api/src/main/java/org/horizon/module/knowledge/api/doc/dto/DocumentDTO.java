package org.horizon.module.knowledge.api.doc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Schema(description = "RPC - 文档信息 DTO")
@Data
public class DocumentDTO implements Serializable {

    @Schema(description = "文件 ID（上传接口返回的 fileId）", requiredMode = Schema.RequiredMode.REQUIRED, example = "file-123")
    @NotEmpty
    private String id;

    @Schema(description = "文件名", requiredMode = Schema.RequiredMode.REQUIRED, example = "政策解读.pdf")
    @NotEmpty
    private String fileName;

    @Schema(description = "文件类型", example = "application/pdf")
    private String mimeType;

    @Schema(description = "字数", example = "2048")
    private Integer length;

    @Schema(description = "页数", example = "12")
    private Integer pagesCount;
}