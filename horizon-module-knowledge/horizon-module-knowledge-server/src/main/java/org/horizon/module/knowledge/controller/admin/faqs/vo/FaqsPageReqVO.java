package org.horizon.module.knowledge.controller.admin.faqs.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）分页 Request VO")
@Data
public class FaqsPageReqVO extends PageParam {

    @Schema(description = "知識庫ノードID（knowledge_nodes.id）", example = "809")
    private Long nodeId;

    @Schema(description = "知識庫文档版本ID（knowledge_doc_versions.id）", example = "19668")
    private Long knowledgeDocVersionsId;

    @Schema(description = "FAQ来源（manual/doc/import/auto）", example = "1")
    private String sourceType;

    @Schema(description = "ユーザー提问")
    private String question;

    @Schema(description = "回答内容")
    private String answer;

    @Schema(description = "言語ID (例: ja, en)", example = "27977")
    private String langId;

    @Schema(description = "表示状態（pending/public/internal/private/archived）")
    private String displayFlag;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
