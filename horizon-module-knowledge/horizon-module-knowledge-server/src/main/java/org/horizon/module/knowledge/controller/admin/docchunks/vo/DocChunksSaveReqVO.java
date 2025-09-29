package org.horizon.module.knowledge.controller.admin.docchunks.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG 文書切片表（文档分段后存储内容）新增/修改 Request VO")
@Data
public class DocChunksSaveReqVO {

    @Schema(description = "知识库文档切片ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1538")
    private Long id;

    @Schema(description = "知识库文档版本ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8333")
    @NotNull(message = "知识库文档版本ID不能为空")
    private Long knowledgeDocVersionsId;

    @Schema(description = "文本内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "文本内容不能为空")
    private String text;

    @Schema(description = "顺序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "顺序号不能为空")
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

}