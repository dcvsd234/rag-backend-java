package org.horizon.module.qa.controller.admin.userquestions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - RAG 用户问题表（终端用户的每一次提问记录） Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserQuestionsRespVO {

    @Schema(description = "问题ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31168")
    @ExcelProperty("问题ID")
    private Long id;

    @Schema(description = "注册用户ID（若有登录则填，否则为空）", example = "32322")
    @ExcelProperty("注册用户ID（若有登录则填，否则为空）")
    private Long userId;

    @Schema(description = "匿名用户ID（未注册用户使用）", example = "1500")
    @ExcelProperty("匿名用户ID（未注册用户使用）")
    private Object anonUserId;

    @Schema(description = "会话标识（用于多轮对话追踪；前端携带）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18666")
    @ExcelProperty("会话标识（用于多轮对话追踪；前端携带）")
    private String sessionId;

    @Schema(description = "语言（ja/en/zh/auto），默认ja", requiredMode = Schema.RequiredMode.REQUIRED, example = "28600")
    @ExcelProperty("语言（ja/en/zh/auto），默认ja")
    private String langId;

    @Schema(description = "用户原始提问", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户原始提问")
    private String originalText;

    @Schema(description = "翻译后的文本（例如统一为日语以便检索）")
    @ExcelProperty("翻译后的文本（例如统一为日语以便检索）")
    private String translatedText;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
