package org.horizon.module.knowledge.controller.admin.docs.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - RAG 知識庫文書 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DocsRespVO {

    @Schema(description = "知识库文档ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16788")
    @ExcelProperty("知识库文档ID")
    private Long id;

    @Schema(description = "知识库节点", requiredMode = Schema.RequiredMode.REQUIRED, example = "4810")
    @ExcelProperty("知识库节点")
    private Long nodeId;

    @Schema(description = "FK → knowledge_doc_versions.id", example = "1426")
    @ExcelProperty("FK → knowledge_doc_versions.id")
    private Long activeVersionId;

    @Schema(description = "贡献者名字", example = "张三")
    @ExcelProperty("贡献者名字")
    private String attributionName;

    @Schema(description = "pending、public、internal、private、archived", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("pending、public、internal、private、archived")
    private Integer displayFlag;

    @Schema(description = "備考", example = "随便")
    @ExcelProperty("備考")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
