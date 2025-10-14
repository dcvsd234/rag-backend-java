
package org.horizon.module.trends.controller.admin.vo.keyword;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 管理后台 - 关键词新增/修改 Request VO
 */
@Schema(description = "管理后台 - 关键词新增/修改 Request VO")
@Data
public class KeywordSaveReqVO {

    @Schema(description = "ID", example = "30092")
    private Long keywordId;

    @Schema(description = "keywordText", example = "Logic")
    @NotNull(message = "文本不能为空")
    private String keywordText;

    @Schema(description = "租户ID（多租户隔离）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;
}