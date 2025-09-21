package org.horizon.module.knowledge.controller.admin.nodes.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 管理后台 - RAG 知识库节点新增/修改 Request VO
 */
@Schema(description = "管理后台 - RAG 知识库节点新增/修改 Request VO")
@Data
public class KnowledgeNodesSaveReqVO {

    @Schema(description = "知识库节点ID", example = "30092")
    private Long id;

    @Schema(description = "租户ID（多租户隔离）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

    @Schema(description = "节点名称（展示给用户，如“劳动法”“移民法”）", requiredMode = Schema.RequiredMode.REQUIRED, example = "劳动法")
    @NotEmpty(message = "节点名称（展示给用户，如“劳动法”“移民法”）不能为空")
    private String name;

    @Schema(description = "父节点ID，空表示根节点", example = "7142")
    private Long parentId;

    @Schema(description = "备注", example = "你猜")
    private String remark;
}