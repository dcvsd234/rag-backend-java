package org.horizon.module.knowledge.controller.admin.docs.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG 知識庫文書分页 Request VO")
@Data
public class DocsPageReqVO extends PageParam {

    @Schema(description = "知识库节点", example = "4810")
    private Long nodeId;

    @Schema(description = "FK → knowledge_doc_versions.id", example = "1426")
    private Long activeVersionId;

    @Schema(description = "贡献者名字", example = "张三")
    private String attributionName;

    @Schema(description = "pending、public、internal、private、archived")
    private Integer displayFlag;

    @Schema(description = "備考", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
