package org.horizon.module.dataset.api.vo.doc;

import lombok.Data;

@Data
public class SliceDTO {
    private String docId;
    private String sliceId;
    private String text;
    private Integer index;            // 切片序号
    private Integer beginOffset;      // 可选：在原文中的偏移
    private Integer endOffset;
    private float[] embedding;        // 仅在需要下传时使用；通常不在 API 暴露
}