package org.horizon.module.knowledge.controller.admin.docversions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG 知識庫文書バージョン新增/修改 Request VO")
@Data
public class DocVersionsSaveReqVO {

    @Schema(description = "知识库文档版本ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1562")
    private Long id;

    @Schema(description = "知识库文档ID", example = "21406")
    private Long knowledgeDocsId;

    @Schema(description = "绑定实际存储的文件", requiredMode = Schema.RequiredMode.REQUIRED, example = "27669")
    @NotNull(message = "绑定实际存储的文件不能为空")
    private Long infraFileId;

    @Schema(description = "版本号", example = "2")
    private String versionNo;

    @Schema(description = "绑定实际存储的文件")
    private String summary;

    @Schema(description = "備考", example = "你猜")
    private String remark;

}