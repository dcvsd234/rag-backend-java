package org.horizon.module.qa.controller.admin.usersessions.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG 会话表：一段连续对话的容器（多个问题与回答的集合）分页 Request VO")
@Data
public class UserSessionsPageReqVO extends PageParam {

    @Schema(description = "注册用户ID（匿名时为空）", example = "29047")
    private Long userId;

    @Schema(description = "匿名用户ID（未注册用户使用）", example = "10962")
    private Object anonUserId;

    @Schema(description = "会话标识（前端持有，标识同一段对话）")
    private String sessionToken;

    @Schema(description = "累计上下文 token（用于长度上限控制，可按需维护）")
    private Integer tokenUsed;

    @Schema(description = "最近一次交互时间（每次问/答更新）")
    private LocalDateTime lastActivity;

    @Schema(description = "会话开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "会话结束时间（关闭/过期写入）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "会话状态：active/closed/expired", example = "1")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
