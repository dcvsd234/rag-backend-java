package org.horizon.module.knowledge.controller.admin.docchunks.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG 文書切片表（文档分段后存储内容）分页 Request VO")
@Data
public class DocChunksPageReqVO extends PageParam {

    @Schema(description = "知识库文档版本ID", example = "8333")
    private Long knowledgeDocVersionsId;

    @Schema(description = "文本内容")
    private String text;

    @Schema(description = "顺序号")
    private Integer chunkIndex;

    @Schema(description = "起始字符位置（原始文档中的偏移量）")
    private Integer startOffset;

    @Schema(description = "结束字符位置（原始文档中的偏移量）")
    private Integer endOffset;

    @Schema(description = "来源页码")
    private Integer pageNo;

    @Schema(description = "层级标题路径")
    private String headingPath;

    @Schema(description = "備考", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
