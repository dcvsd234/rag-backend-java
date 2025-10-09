package org.horizon.module.knowledge.controller.admin.faqcategorylinks.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - FAQ分類关联新增/修改 Request VO")
@Data
public class FaqCategoryLinksSaveReqVO {

    @Schema(description = "FAQ分类关联表ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26906")
    private Long id;

    @Schema(description = "FAQ ID（knowledge_faqs.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29109")
    @NotNull(message = "FAQ ID（knowledge_faqs.id）不能为空")
    private Long faqId;

    @Schema(description = "分類ID（knowledge_categories.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20473")
    @NotNull(message = "分類ID（knowledge_categories.id）不能为空")
    private Long categoryId;

    @Schema(description = "关联强度（人工=1.00，模型<1.0）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联强度（人工=1.00，模型<1.0）不能为空")
    private Double confidence;

    @Schema(description = "关联来源（manual / rule / model）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联来源（manual / rule / model）不能为空")
    private String linkSource;

}
