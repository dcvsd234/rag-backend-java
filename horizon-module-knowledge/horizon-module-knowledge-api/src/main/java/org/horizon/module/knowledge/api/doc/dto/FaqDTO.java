package org.horizon.module.knowledge.api.doc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Schema(description = "RPC - FAQ DTO")
@Data
public class FaqDTO implements Serializable {

    @Schema(description = "序号", example = "1")
    private Integer idx;

    @Schema(description = "FAQ 唯一 ID", example = "faq-abc123")
    private String id;

    @Schema(description = "问题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String question;

    @Schema(description = "分类词（多个）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private List<String> categories;

    @Schema(description = "答案", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String answer;

    @Schema(description = "状态", example = "faq")
    private String status;
}
