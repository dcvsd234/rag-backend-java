package org.horizon.module.dataset.controller.admin.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "App - QA 预览请求 VO")
@Data
public class QaPreviewReqVO {

    @Schema(description = "用户问题，对应 {policy_text}", example = "新しい制度では、再生可能エネルギーの導入が加速されますか？")
    @NotBlank(message = "用户问题不能为空")
    private String question;

    @Schema(description = "提示词模板，包含 {policy_text}, {evidence} 占位符", example = "あなたは日本語のQAアシスタントです…")
    @NotBlank(message = "提示词模板不能为空")
    private String template;

    @Schema(description = "证据列表", example = "[\"制度は再エネ比率を高める\", \"自治体に基準を課す\"]")
    @NotEmpty(message = "证据列表不能为空")
    private List<String> evidences;

}