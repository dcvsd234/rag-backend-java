package org.horizon.module.knowledge.controller.admin.faqs.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）新增/修改 Request VO")
@Data
public class FaqsSaveReqVO {

    @Schema(description = "FAQ主表ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4489")
    private Long id;

    @Schema(description = "知識庫ノードID（knowledge_nodes.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "809")
    @NotNull(message = "知識庫ノードID（knowledge_nodes.id）不能为空")
    private Long nodeId;

    @Schema(description = "知識庫文档版本ID（knowledge_doc_versions.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "19668")
    @NotNull(message = "知識庫文档版本ID（knowledge_doc_versions.id）不能为空")
    private Long knowledgeDocVersionsId;

    @Schema(description = "FAQ来源（manual/doc/import/auto）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "FAQ来源（manual/doc/import/auto）不能为空")
    private String sourceType;

    @Schema(description = "ユーザー提问", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "ユーザー提问不能为空")
    private String question;

    @Schema(description = "回答内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "回答内容不能为空")
    private String answer;

    @Schema(description = "言語ID (例: ja, en)", requiredMode = Schema.RequiredMode.REQUIRED, example = "27977")
    @NotEmpty(message = "言語ID (例: ja, en)不能为空")
    private String langId;

    @Schema(description = "表示状態（pending/public/internal/private/archived）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "表示状態（pending/public/internal/private/archived）不能为空")
    private String displayFlag;

}