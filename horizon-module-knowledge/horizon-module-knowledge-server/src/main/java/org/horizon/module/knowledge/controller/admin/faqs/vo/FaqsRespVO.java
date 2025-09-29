package org.horizon.module.knowledge.controller.admin.faqs.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点） Response VO")
@Data
@ExcelIgnoreUnannotated
public class FaqsRespVO {

    @Schema(description = "FAQ主表ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4489")
    @ExcelProperty("FAQ主表ID")
    private Long id;

    @Schema(description = "知識庫ノードID（knowledge_nodes.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "809")
    @ExcelProperty("知識庫ノードID（knowledge_nodes.id）")
    private Long nodeId;

    @Schema(description = "知識庫文档版本ID（knowledge_doc_versions.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "19668")
    @ExcelProperty("知識庫文档版本ID（knowledge_doc_versions.id）")
    private Long knowledgeDocVersionsId;

    @Schema(description = "FAQ来源（manual/doc/import/auto）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("FAQ来源（manual/doc/import/auto）")
    private String sourceType;

    @Schema(description = "ユーザー提问", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("ユーザー提问")
    private String question;

    @Schema(description = "回答内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("回答内容")
    private String answer;

    @Schema(description = "言語ID (例: ja, en)", requiredMode = Schema.RequiredMode.REQUIRED, example = "27977")
    @ExcelProperty("言語ID (例: ja, en)")
    private String langId;

    @Schema(description = "表示状態（pending/public/internal/private/archived）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("表示状態（pending/public/internal/private/archived）")
    private String displayFlag;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}