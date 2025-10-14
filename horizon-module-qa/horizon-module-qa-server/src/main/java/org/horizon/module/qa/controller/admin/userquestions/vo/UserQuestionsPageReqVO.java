package org.horizon.module.qa.controller.admin.userquestions.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG 用户问题表（终端用户的每一次提问记录）分页 Request VO")
@Data
public class UserQuestionsPageReqVO extends PageParam {

    @Schema(description = "注册用户ID（若有登录则填，否则为空）", example = "32322")
    private Long userId;

    @Schema(description = "匿名用户ID（未注册用户使用）", example = "1500")
    private Object anonUserId;

    @Schema(description = "会话标识（用于多轮对话追踪；前端携带）", example = "18666")
    private String sessionId;

    @Schema(description = "语言（ja/en/zh/auto），默认ja", example = "28600")
    private String langId;

    @Schema(description = "用户原始提问")
    private String originalText;

    @Schema(description = "翻译后的文本（例如统一为日语以便检索）")
    private String translatedText;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}