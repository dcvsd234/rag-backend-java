package org.horizon.module.knowledge.controller.admin.categoryembeddings.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG FAQ 分类向量新增/修改 Request VO")
@Data
public class CategoryEmbeddingsSaveReqVO {

    @Schema(description = "分类向量ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26943")
    private Long id;

    @Schema(description = "FAQ 分类ID (FK → knowledge_categories.id)", requiredMode = Schema.RequiredMode.REQUIRED, example = "6827")
    @NotNull(message = "FAQ 分类ID (FK → knowledge_categories.id)不能为空")
    private Long categoryId;

    @Schema(description = "向量模型名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "向量模型名不能为空")
    private String modelName;

    @Schema(description = "模型版本", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模型版本不能为空")
    private String modelVersion;

    @Schema(description = "提供方（ELYZA/voyage/local等）")
    private String provider;

    @Schema(description = "隔离域 (例: tenant:1|kb:category)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "隔离域 (例: tenant:1|kb:category)不能为空")
    private String namespace;

    @Schema(description = "距离度量（cosine/L2/ip）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "距离度量（cosine/L2/ip）不能为空")
    private String metric;

    @Schema(description = "向量维度 (固定 2048)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "向量维度 (固定 2048)不能为空")
    private Integer dim;

    @Schema(description = "向量列 (pgvector halfvec, 2048维)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "向量列 (pgvector halfvec, 2048维)不能为空")
    private Object embedding;

}