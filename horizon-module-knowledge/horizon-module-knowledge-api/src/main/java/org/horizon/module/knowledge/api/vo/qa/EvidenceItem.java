package org.horizon.module.knowledge.api.vo.qa;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EvidenceItem {
    @NotBlank
    private String text;
    private String docId;
    private String sliceId;
    private Integer index;           // 在证据列表中的顺序
    private String source;           // 可选：文件名/URL
}