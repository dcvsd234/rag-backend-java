package org.horizon.module.qa.controller.admin.userquestions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG 用户问题表（终端用户的每一次提问记录）新增/修改 Request VO")
@Data
public class UserQuestionsSaveReqVO {

    @Schema(description = "问题ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31168")
    private Long id;

    @Schema(description = "注册用户ID（若有登录则填，否则为空）", example = "32322")
    private Long userId;

    @Schema(description = "匿名用户ID（未注册用户使用）", example = "1500")
    private Object anonUserId;

    @Schema(description = "会话标识（用于多轮对话追踪；前端携带）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18666")
    @NotEmpty(message = "会话标识（用于多轮对话追踪；前端携带）不能为空")
    private String sessionId;

    @Schema(description = "语言（ja/en/zh/auto），默认ja", requiredMode = Schema.RequiredMode.REQUIRED, example = "28600")
    @NotEmpty(message = "语言（ja/en/zh/auto），默认ja不能为空")
    private String langId;

    @Schema(description = "用户原始提问", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "用户原始提问不能为空")
    private String originalText;

    @Schema(description = "翻译后的文本（例如统一为日语以便检索）")
    private String translatedText;

}