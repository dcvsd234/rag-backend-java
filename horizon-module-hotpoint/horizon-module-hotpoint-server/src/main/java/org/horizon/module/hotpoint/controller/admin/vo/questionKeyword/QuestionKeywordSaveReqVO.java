package org.horizon.module.hotpoint.controller.admin.vo.questionKeyword;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * 管理后台 - 问题-关键词新增/修改 Request VO
 */
@Schema(description = "管理后台 - 问题新增/修改 Request VO")
@Data
public class QuestionKeywordSaveReqVO {

    @Schema(description = "ID", example = "30092")
    private Long id;

    @Schema(description = "租户ID（多租户隔离）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

    @Schema(description = "问题id", requiredMode = Schema.RequiredMode.REQUIRED, example = "谷歌的钻前模式？")
    @NotEmpty(message = "问题id不能为空")
    private String question_id;

    @Schema(description = "关键词id", requiredMode = Schema.RequiredMode.REQUIRED, example = "谷歌的钻前模式？")
    @NotEmpty(message = "关键词id不能为空")
    private String keyword_id;
}