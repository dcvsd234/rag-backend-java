package org.horizon.module.hotpoint.controller.admin.vo.keyword;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 关键词 Response VO")
@Data
public class KeywordRespVO {

    @Schema(description = "关键词序号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String text;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "时间戳格式")
    private LocalDateTime createTime;

}
