package org.horizon.module.qa.controller.admin.usersessions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - RAG 会话表：一段连续对话的容器（多个问题与回答的集合）新增/修改 Request VO")
@Data
public class UserSessionsSaveReqVO {

    @Schema(description = "会话ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27120")
    private Long id;

    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long tenantId;

    @Schema(description = "注册用户ID（匿名时为空）", example = "29047")
    private Long userId;

    @Schema(description = "会话标识（前端持有，标识同一段对话）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sessionToken;

    @Schema(description = "累计上下文 token（用于长度上限控制，可按需维护）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer tokenUsed;

    @Schema(description = "最近一次交互时间（每次问/答更新）", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime lastActivity;

    @Schema(description = "会话开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime startTime;

    @Schema(description = "会话结束时间（关闭/过期写入）")
    private LocalDateTime endTime;

    @Schema(description = "会话状态：active/closed/expired", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "会话状态：active/closed/expired不能为空")
    private String status;

}
