package org.horizon.module.knowledge.controller.admin.categoryembeddings.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - RAG FAQ 分类向量 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CategoryEmbeddingsRespVO {

    @Schema(description = "分类向量ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26943")
    @ExcelProperty("分类向量ID")
    private Long id;

    @Schema(description = "FAQ 分类ID (FK → knowledge_categories.id)", requiredMode = Schema.RequiredMode.REQUIRED, example = "6827")
    @ExcelProperty("FAQ 分类ID (FK → knowledge_categories.id)")
    private Long categoryId;

    @Schema(description = "向量模型名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("向量模型名")
    private String modelName;

    @Schema(description = "模型版本", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("模型版本")
    private String modelVersion;

    @Schema(description = "提供方（ELYZA/voyage/local等）")
    @ExcelProperty("提供方（ELYZA/voyage/local等）")
    private String provider;

    @Schema(description = "隔离域 (例: tenant:1|kb:category)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("隔离域 (例: tenant:1|kb:category)")
    private String namespace;

    @Schema(description = "距离度量（cosine/L2/ip）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("距离度量（cosine/L2/ip）")
    private String metric;

    @Schema(description = "向量维度 (固定 2048)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("向量维度 (固定 2048)")
    private Integer dim;

    @Schema(description = "向量列 (pgvector halfvec, 2048维)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("向量列 (pgvector halfvec, 2048维)")
    private Object embedding;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
