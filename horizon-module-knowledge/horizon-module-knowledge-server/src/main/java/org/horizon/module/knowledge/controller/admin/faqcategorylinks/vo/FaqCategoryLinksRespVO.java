package org.horizon.module.knowledge.controller.admin.faqcategorylinks.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - FAQ分類关联 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FaqCategoryLinksRespVO {

    @Schema(description = "FAQ分类关联表ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26906")
    @ExcelProperty("FAQ分类关联表ID")
    private Long id;

    @Schema(description = "FAQ ID（knowledge_faqs.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29109")
    @ExcelProperty("FAQ ID（knowledge_faqs.id）")
    private Long faqId;

    @Schema(description = "分類ID（knowledge_categories.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20473")
    @ExcelProperty("分類ID（knowledge_categories.id）")
    private Long categoryId;

    @Schema(description = "关联强度（人工=1.00，模型<1.0）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联强度（人工=1.00，模型<1.0）")
    private Double confidence;

    @Schema(description = "关联来源（manual / rule / model）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联来源（manual / rule / model）")
    private String linkSource;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
