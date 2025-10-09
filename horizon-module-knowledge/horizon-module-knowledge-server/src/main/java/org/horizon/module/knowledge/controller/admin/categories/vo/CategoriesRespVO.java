package org.horizon.module.knowledge.controller.admin.categories.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - RAG FAQ 分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CategoriesRespVO {

    @Schema(description = "FAQ分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11804")
    @ExcelProperty("FAQ分类ID")
    private Long id;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类名称")
    private String question;

    @Schema(description = "语言ID（如 ja / en）", requiredMode = Schema.RequiredMode.REQUIRED, example = "17472")
    @ExcelProperty("语言ID（如 ja / en）")
    private String langId;

    @Schema(description = "显示标志 (pending/public/internal/private/archived)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("显示标志 (pending/public/internal/private/archived)")
    private String displayFlag;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
