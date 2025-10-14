package org.horizon.module.trends.controller.admin.vo.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * 管理后台 - 问题新增/修改 Request VO
 */
@Schema(description = "管理后台 - 问题新增/修改 Request VO")
@Data
public class QuestionSaveReqVO {

    @Schema(description = "ID", example = "30092")
    private Long id;

    @Schema(description = "租户ID（多租户隔离）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

    @Schema(description = "问题原始内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "谷歌的钻前模式？")
    @NotEmpty(message = "问题的原始内容不能为空")
    private String text;


    @Schema(description = "问题格式化内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "谷歌公司的赚钱模式是什么？")
    @NotEmpty(message = "问题的格式化内容不能为空")
    private String normalizedText;

    @Schema(description = "问题的答案", requiredMode = Schema.RequiredMode.REQUIRED, example = "广告")
    @NotEmpty(message = "问题的答案不能为空")
    private String answerText;

    @Schema(description = "问题被提问的次数", example = "2")
    private Long askCount;

    @Schema(description = "用户id，表示提问的人", example = "7142")
    private Long userId;

    @Schema(description = "备注", example = "你猜")
    private String remark;
}