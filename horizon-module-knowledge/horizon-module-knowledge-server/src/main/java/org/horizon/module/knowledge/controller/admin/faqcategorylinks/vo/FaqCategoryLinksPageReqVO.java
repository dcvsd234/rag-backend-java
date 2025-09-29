package org.horizon.module.knowledge.controller.admin.faqcategorylinks.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - FAQ分類关联分页 Request VO")
@Data
public class FaqCategoryLinksPageReqVO extends PageParam {

    @Schema(description = "FAQ ID（knowledge_faqs.id）", example = "29109")
    private Long faqId;

    @Schema(description = "分類ID（knowledge_categories.id）", example = "20473")
    private Long categoryId;

    @Schema(description = "关联强度（人工=1.00，模型<1.0）")
    private Double confidence;

    @Schema(description = "关联来源（manual / rule / model）")
    private String linkSource;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
