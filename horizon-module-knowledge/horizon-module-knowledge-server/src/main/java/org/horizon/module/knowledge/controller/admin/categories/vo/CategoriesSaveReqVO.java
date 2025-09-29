package org.horizon.module.knowledge.controller.admin.categories.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - RAG FAQ 分类新增/修改 Request VO")
@Data
public class CategoriesSaveReqVO {

    @Schema(description = "FAQ分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11804")
    private Long id;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类名称不能为空")
    private String question;

    @Schema(description = "语言ID（如 ja / en）", requiredMode = Schema.RequiredMode.REQUIRED, example = "17472")
    @NotEmpty(message = "语言ID（如 ja / en）不能为空")
    private String langId;

    @Schema(description = "显示标志 (pending/public/internal/private/archived)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "显示标志 (pending/public/internal/private/archived)不能为空")
    private String displayFlag;

}