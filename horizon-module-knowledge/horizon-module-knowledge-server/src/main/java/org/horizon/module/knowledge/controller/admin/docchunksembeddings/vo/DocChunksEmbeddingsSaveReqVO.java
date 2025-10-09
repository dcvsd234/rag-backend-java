package org.horizon.module.knowledge.controller.admin.docchunksembeddings.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG 文書向量表（存储文本切片的向量表示）新增/修改 Request VO")
@Data
public class DocChunksEmbeddingsSaveReqVO {

    @Schema(description = "知识库文档切片向量ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25341")
    private Long id;

    @Schema(description = "切片ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7175")
    @NotNull(message = "切片ID不能为空")
    private Long chunkId;

    @Schema(description = "向量模型名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "向量模型名不能为空")
    private String modelName;

    @Schema(description = "模型版本号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模型版本号不能为空")
    private String modelVersion;

    @Schema(description = "模型提供方", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模型提供方不能为空")
    private String provider;

    @Schema(description = "隔离域（多租户 + 知识库上下文）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "隔离域（多租户 + 知识库上下文）不能为空")
    private String namespace;

    @Schema(description = "距离度量方式（cosine / L2 / ip）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "距离度量方式（cosine / L2 / ip）不能为空")
    private String metric;

    @Schema(description = "向量维度（固定 2048）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "向量维度（固定 2048）不能为空")
    private Integer dim;

    @Schema(description = "向量列（pgvector halfvec，2048 维）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "向量列（pgvector halfvec，2048 维）不能为空")
    private Object embedding;

}