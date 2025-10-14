package org.horizon.module.qa.controller.admin.useranswersources.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）分页 Request VO")
@Data
public class UserAnswerSourcesPageReqVO extends PageParam {

    @Schema(description = "对应的问题ID（qa_user_questions.id）", example = "32462")
    private Long questionsId;

    @Schema(description = "来源类型（faq/doc/chunk/ai）", example = "1")
    private String sourceType;

    @Schema(description = "具体来源ID（faq_id/doc_id/chunk_id）", example = "13797")
    private Long sourceId;

    @Schema(description = "片段内起始偏移")
    private Integer spanStart;

    @Schema(description = "片段内结束偏移")
    private Integer spanEnd;

    @Schema(description = "相似度得分")
    private Double score;

    @Schema(description = "排名顺序")
    private Integer rank;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}