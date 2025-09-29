package org.horizon.module.knowledge.controller.admin.docchunks.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - RAG 文書切片表（文档分段后存储内容） Response VO")
@Data
@ExcelIgnoreUnannotated
public class DocChunksRespVO {

    @Schema(description = "知识库文档切片ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1538")
    @ExcelProperty("知识库文档切片ID")
    private Long id;

    @Schema(description = "知识库文档版本ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8333")
    @ExcelProperty("知识库文档版本ID")
    private Long knowledgeDocVersionsId;

    @Schema(description = "文本内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("文本内容")
    private String text;

    @Schema(description = "顺序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("顺序号")
    private Integer chunkIndex;

    @Schema(description = "起始字符位置（原始文档中的偏移量）")
    @ExcelProperty("起始字符位置（原始文档中的偏移量）")
    private Integer startOffset;

    @Schema(description = "结束字符位置（原始文档中的偏移量）")
    @ExcelProperty("结束字符位置（原始文档中的偏移量）")
    private Integer endOffset;

    @Schema(description = "来源页码")
    @ExcelProperty("来源页码")
    private Integer pageNo;

    @Schema(description = "层级标题路径")
    @ExcelProperty("层级标题路径")
    private String headingPath;

    @Schema(description = "備考", example = "你说的对")
    @ExcelProperty("備考")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}