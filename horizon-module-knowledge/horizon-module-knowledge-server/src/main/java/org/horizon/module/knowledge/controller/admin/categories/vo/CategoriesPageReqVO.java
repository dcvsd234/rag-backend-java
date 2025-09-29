package org.horizon.module.knowledge.controller.admin.categories.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG FAQ 分类分页 Request VO")
@Data
public class CategoriesPageReqVO extends PageParam {

    @Schema(description = "分类名称")
    private String question;

    @Schema(description = "语言ID（如 ja / en）", example = "17472")
    private String langId;

    @Schema(description = "显示标志 (pending/public/internal/private/archived)")
    private String displayFlag;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}