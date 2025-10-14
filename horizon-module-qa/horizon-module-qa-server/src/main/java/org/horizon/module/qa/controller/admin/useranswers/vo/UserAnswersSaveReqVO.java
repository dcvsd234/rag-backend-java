package org.horizon.module.qa.controller.admin.useranswers.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG 用户回复表（保存终端用户每个提问对应的模型回复）新增/修改 Request VO")
@Data
public class UserAnswersSaveReqVO {

    @Schema(description = "回复ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25209")
    private Long id;

    @Schema(description = "注册用户ID（若有登录则填，否则为空）", example = "4442")
    private Long userId;

    @Schema(description = "对应的问题ID（qa_user_questions.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30350")
    @NotNull(message = "对应的问题ID（qa_user_questions.id）不能为空")
    private Long questionsId;

    @Schema(description = "语言（ja/en/zh/auto），默认ja", requiredMode = Schema.RequiredMode.REQUIRED, example = "782")
    @NotEmpty(message = "语言（ja/en/zh/auto），默认ja不能为空")
    private String langId;

    @Schema(description = "返回给用户的最终答案", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "返回给用户的最终答案不能为空")
    private String answerText;

    @Schema(description = "翻译后的文本（终端用户母语）")
    private String translatedText;

    @Schema(description = "使用的模型名称（plamo-7B, mistral 等）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "使用的模型名称（plamo-7B, mistral 等）不能为空")
    private String modelName;

}
