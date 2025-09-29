package org.horizon.module.knowledge.controller.admin.docversions.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG 知識庫文書バージョン分页 Request VO")
@Data
public class DocVersionsPageReqVO extends PageParam {

    @Schema(description = "知识库文档ID", example = "21406")
    private Long knowledgeDocsId;

    @Schema(description = "绑定实际存储的文件", example = "27669")
    private Long infraFileId;

    @Schema(description = "版本号")
    private String versionNo;

    @Schema(description = "绑定实际存储的文件")
    private String summary;

    @Schema(description = "備考", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}