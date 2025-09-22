package org.horizon.module.knowledge.api.vo.qa;

import lombok.Data;

import java.util.List;

@Data
public class PreviewQAResp {
    private String answer;           // 生成答案（可两行/JSON 取决于 outputMode）
    private String rawOutput;        // 可选：排障
    private List<EvidenceItem> used; // 实际被模型采纳的证据（可选）
    private String model;            // 实际命中的生成模型
    private Long runtimeMs;
    private String traceId;
}