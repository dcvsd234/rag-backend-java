package org.horizon.module.knowledge.controller.admin.docs.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG 知識庫文書新增/修改 Request VO")
@Data
public class DocsSaveReqVO {

    @Schema(description = "知识库文档ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16788")
    private Long id;


    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4810")
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

    @Schema(description = "知识库节点", requiredMode = Schema.RequiredMode.REQUIRED, example = "4810")
    @NotNull(message = "知识库节点不能为空")
    private Long nodeId;

    @Schema(description = "FK → knowledge_doc_versions.id", example = "1426")
    private Long activeVersionId;

    @Schema(description = "贡献者名字", example = "张三")
    private String attributionName;

    @Schema(description = "pending、public、internal、private、archived", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "pending、public、internal、private、archived不能为空")
    private Integer displayFlag;

    @Schema(description = "備考", example = "随便")
    private String remark;

}
