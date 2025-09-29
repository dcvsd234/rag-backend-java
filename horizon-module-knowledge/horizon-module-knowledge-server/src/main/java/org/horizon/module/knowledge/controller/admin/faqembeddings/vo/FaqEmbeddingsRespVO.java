package org.horizon.module.knowledge.controller.admin.faqembeddings.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - FAQ 向量テーブル（FAQ 文本のベクトル表現を保存） Response VO")
@Data
@ExcelIgnoreUnannotated
public class FaqEmbeddingsRespVO {

    @Schema(description = "FAQ向量ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22513")
    @ExcelProperty("FAQ向量ID")
    private Long id;

    @Schema(description = "FAQ ID（knowledge_faqs.id への参照を推奨）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30206")
    @ExcelProperty("FAQ ID（knowledge_faqs.id への参照を推奨）")
    private Long faqId;

    @Schema(description = "向量模型名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("向量模型名")
    private String modelName;

    @Schema(description = "模型版本号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("模型版本号")
    private String modelVersion;

    @Schema(description = "模型提供方", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("模型提供方")
    private String provider;

    @Schema(description = "隔离域（テナント／FAQ）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("隔离域（テナント／FAQ）")
    private String namespace;

    @Schema(description = "距离度量方式（cosine / L2 / ip）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("距离度量方式（cosine / L2 / ip）")
    private String metric;

    @Schema(description = "向量维度（固定 2048）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("向量维度（固定 2048）")
    private Integer dim;

    @Schema(description = "向量列（pgvector halfvec，2048 维）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("向量列（pgvector halfvec，2048 维）")
    private Object embedding;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
