package org.horizon.module.qa.controller.admin.usersessions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - RAG 会话表：一段连续对话的容器（多个问题与回答的集合） Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserSessionsRespVO {

    @Schema(description = "会话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27120")
    @ExcelProperty("会话ID")
    private Long id;

    @Schema(description = "注册用户ID（匿名时为空）", example = "29047")
    @ExcelProperty("注册用户ID（匿名时为空）")
    private Long userId;

    @Schema(description = "匿名用户ID（未注册用户使用）", example = "10962")
    @ExcelProperty("匿名用户ID（未注册用户使用）")
    private Object anonUserId;

    @Schema(description = "会话标识（前端持有，标识同一段对话）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("会话标识（前端持有，标识同一段对话）")
    private String sessionToken;

    @Schema(description = "累计上下文 token（用于长度上限控制，可按需维护）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("累计上下文 token（用于长度上限控制，可按需维护）")
    private Integer tokenUsed;

    @Schema(description = "最近一次交互时间（每次问/答更新）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("最近一次交互时间（每次问/答更新）")
    private LocalDateTime lastActivity;

    @Schema(description = "会话开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("会话开始时间")
    private LocalDateTime startTime;

    @Schema(description = "会话结束时间（关闭/过期写入）")
    @ExcelProperty("会话结束时间（关闭/过期写入）")
    private LocalDateTime endTime;

    @Schema(description = "会话状态：active/closed/expired", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("会话状态：active/closed/expired")
    private String status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
