package org.horizon.module.knowledge.controller.admin.document.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - FAQ VO")
@Data
public class FaqVO {

    @Schema(description = "序号", example = "1")
    private Integer idx;

    @Schema(description = "FAQ 唯一 ID", example = "faq-abc123")
    private String id;

    @Schema(description = "问题", requiredMode = Schema.RequiredMode.REQUIRED, example = "新的制度是否会加速可再生能源的引入？")
    @NotEmpty(message = "问题不能为空")
    private String question;

    @Schema(description = "分类词", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\"policy\", \"energy\"]")
    @NotEmpty(message = "分类词不能为空")
    private List<String> categories;

    @Schema(description = "答案", requiredMode = Schema.RequiredMode.REQUIRED, example = "会。新的制度明确提出加快可再生能源引入。")
    @NotEmpty(message = "答案不能为空")
    private String answer;

    @Schema(description = "状态", example = "faq")
    private String status;
}