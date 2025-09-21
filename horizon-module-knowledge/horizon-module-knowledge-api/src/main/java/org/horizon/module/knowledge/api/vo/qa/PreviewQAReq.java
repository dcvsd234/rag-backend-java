package org.horizon.module.knowledge.api.vo.qa;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Data
public class PreviewQAReq {
    @NotBlank
    private String tenantId;

    /** 用户输入的问题 */
    @NotBlank
    private String question;

    /** 提示词（可让前端编辑） */
    @NotBlank
    private String promptTemplate;

    /** 多条证据（来自文档多段复制） */
    @NotEmpty
    private List<EvidenceItem> evidences;

    /** 透传额外参数：如 genParams、outputMode 等 */
    private Map<String, Object> extra;
}