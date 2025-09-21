package org.horizon.module.knowledge.controller.admin.nodes.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.horizon.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.horizon.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - RAG 知识库节点分页 Request VO")
@Data
public class KnowledgeNodesPageReqVO extends PageParam {

    @Schema(description = "租户SID，多租户隔离", example = "16439")
    private Long tenantId;

    @Schema(description = "节点名称（展示给用户，如“劳动法”“移民法”）", example = "劳动法")
    private String name;

    @Schema(description = "父节点ID，空表示根节点", example = "7142")
    private Long parentId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "是否删除：有效=FALSE，逻辑删除=TRUE")
    private Boolean deleted;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}