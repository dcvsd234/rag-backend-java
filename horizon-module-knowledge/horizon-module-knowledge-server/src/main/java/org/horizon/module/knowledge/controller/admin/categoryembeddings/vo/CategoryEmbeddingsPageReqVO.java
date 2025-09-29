package org.horizon.module.knowledge.controller.admin.categoryembeddings.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG FAQ 分类向量分页 Request VO")
@Data
public class CategoryEmbeddingsPageReqVO extends PageParam {

    @Schema(description = "FAQ 分类ID (FK → knowledge_categories.id)", example = "6827")
    private Long categoryId;

    @Schema(description = "向量模型名", example = "李四")
    private String modelName;

    @Schema(description = "模型版本")
    private String modelVersion;

    @Schema(description = "提供方（ELYZA/voyage/local等）")
    private String provider;

    @Schema(description = "隔离域 (例: tenant:1|kb:category)")
    private String namespace;

    @Schema(description = "距离度量（cosine/L2/ip）")
    private String metric;

    @Schema(description = "向量维度 (固定 2048)")
    private Integer dim;

    @Schema(description = "向量列 (pgvector halfvec, 2048维)")
    private Object embedding;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
