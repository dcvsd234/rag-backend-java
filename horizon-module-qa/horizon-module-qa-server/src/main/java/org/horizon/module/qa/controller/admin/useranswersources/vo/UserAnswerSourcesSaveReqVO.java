package org.horizon.module.qa.controller.admin.useranswersources.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）新增/修改 Request VO")
@Data
public class UserAnswerSourcesSaveReqVO {

    @Schema(description = "来源记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18637")
    private Long id;

    @Schema(description = "对应的问题ID（qa_user_questions.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "32462")
    @NotNull(message = "对应的问题ID（qa_user_questions.id）不能为空")
    private Long questionsId;

    @Schema(description = "来源类型（faq/doc/chunk/ai）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "来源类型（faq/doc/chunk/ai）不能为空")
    private String sourceType;

    @Schema(description = "具体来源ID（faq_id/doc_id/chunk_id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13797")
    @NotNull(message = "具体来源ID（faq_id/doc_id/chunk_id）不能为空")
    private Long sourceId;

    @Schema(description = "片段内起始偏移")
    private Integer spanStart;

    @Schema(description = "片段内结束偏移")
    private Integer spanEnd;

    @Schema(description = "相似度得分")
    private Double score;

    @Schema(description = "排名顺序")
    private Integer rank;

}
