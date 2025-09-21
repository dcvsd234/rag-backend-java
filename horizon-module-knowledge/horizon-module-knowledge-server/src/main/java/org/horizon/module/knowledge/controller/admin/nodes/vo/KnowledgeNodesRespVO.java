package org.horizon.module.knowledge.controller.admin.nodes.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - RAG 知识库节点 Response VO")
@Data
@ExcelIgnoreUnannotated
public class KnowledgeNodesRespVO {

    @Schema(description = "知识库节点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30092")
    @ExcelProperty("知识库节点ID")
    private Long id;

    @Schema(description = "节点名称（展示给用户，如“劳动法”“移民法”）", requiredMode = Schema.RequiredMode.REQUIRED, example = "劳动法")
    @ExcelProperty("节点名称（展示给用户，如“劳动法”“移民法”）")
    private String name;

    @Schema(description = "父节点ID，空表示根节点", example = "7142")
    @ExcelProperty("父节点ID，空表示根节点")
    private Long parentId;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建者ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建者ID")
    private Long creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}