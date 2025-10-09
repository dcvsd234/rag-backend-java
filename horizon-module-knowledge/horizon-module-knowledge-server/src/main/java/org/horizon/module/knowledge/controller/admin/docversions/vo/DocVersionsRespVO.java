package org.horizon.module.knowledge.controller.admin.docversions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - RAG 知識庫文書バージョン Response VO")
@Data
@ExcelIgnoreUnannotated
public class DocVersionsRespVO {

    @Schema(description = "知识库文档版本ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1562")
    @ExcelProperty("知识库文档版本ID")
    private Long id;

    @Schema(description = "知识库文档ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21406")
    @ExcelProperty("知识库文档ID")
    private Long knowledgeDocsId;

    @Schema(description = "绑定实际存储的文件", requiredMode = Schema.RequiredMode.REQUIRED, example = "27669")
    @ExcelProperty("绑定实际存储的文件")
    private Long infraFileId;

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("版本号")
    private String versionNo;

    @Schema(description = "绑定实际存储的文件")
    @ExcelProperty("绑定实际存储的文件")
    private String summary;

    @Schema(description = "備考", example = "你猜")
    @ExcelProperty("備考")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
