package org.horizon.module.knowledge.api.doc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Schema(description = "RPC - 文档 + FAQ 提交 Request DTO")
@Data
public class SubmitDocReqDTO implements Serializable {

    @Schema(description = "文档数据库 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty
    private String infraFileId;

    @Schema(description = "存储 Key（MinIO 中的路径）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1/leaf-labor/202509/c5450d13-f3c4-4cd1-8170-30f92d3f3e45.xlsx")
    @NotEmpty
    private String key;

    @Schema(description = "FAQ 列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid @NotNull
    private List<FaqDTO> faqs;

    @Schema(description = "知识库文档 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12345")
    @NotEmpty
    private String knowledgeDocsId;


}
