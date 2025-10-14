package org.horizon.module.qa.controller.admin.useranswers.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG 用户回复表（保存终端用户每个提问对应的模型回复）分页 Request VO")
@Data
public class UserAnswersPageReqVO extends PageParam {

    @Schema(description = "注册用户ID（若有登录则填，否则为空）", example = "4442")
    private Long userId;

    @Schema(description = "对应的问题ID（qa_user_questions.id）", example = "30350")
    private Long questionsId;

    @Schema(description = "语言（ja/en/zh/auto），默认ja", example = "782")
    private String langId;

    @Schema(description = "返回给用户的最终答案")
    private String answerText;

    @Schema(description = "翻译后的文本（终端用户母语）")
    private String translatedText;

    @Schema(description = "使用的模型名称（plamo-7B, mistral 等）", example = "李四")
    private String modelName;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
