package org.horizon.module.knowledge.api.vo.qa;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class SaveQATemplateReq {
    @NotBlank
    private String tenantId;

    @NotBlank
    private String question;

    @NotBlank
    private String promptTemplate;

    @NotEmpty
    private List<EvidenceItem> evidences;

    /** 绑定到某个文档（可选） */
    private String docId;

    /** 可选：命名/标签 */
    private String name;
    private List<String> tags;
}
