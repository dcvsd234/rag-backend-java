package org.horizon.module.knowledge.controller.admin.faqembeddings.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）分页 Request VO")
@Data
public class FaqEmbeddingsPageReqVO extends PageParam {

    @Schema(description = "FAQ ID（knowledge_faqs.id への参照を推奨）", example = "30206")
    private Long faqId;

    @Schema(description = "向量模型名", example = "芋艿")
    private String modelName;

    @Schema(description = "模型版本号")
    private String modelVersion;

    @Schema(description = "模型提供方")
    private String provider;

    @Schema(description = "隔离域（テナント／FAQ）")
    private String namespace;

    @Schema(description = "距离度量方式（cosine / L2 / ip）")
    private String metric;

    @Schema(description = "向量维度（固定 2048）")
    private Integer dim;

    @Schema(description = "向量列（pgvector halfvec，2048 维）")
    private Object embedding;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}