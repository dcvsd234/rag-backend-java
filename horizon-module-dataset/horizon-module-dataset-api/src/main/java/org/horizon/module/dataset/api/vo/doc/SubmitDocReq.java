package org.horizon.module.dataset.api.vo.doc;

import lombok.Data;
import org.horizon.module.dataset.api.enums.DocSourceType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class SubmitDocReq {
    @NotBlank
    private String tenantId;

    @NotBlank
    private String docTitle;

    @NotNull
    private DocSourceType sourceType;   // UPLOAD / URL / PASTE

    /** 文档内容三选一：content / url / objectKey(OSS) */
    private String content;
    private String url;
    private String objectKey;

    /** 前端可传的元数据，如业务分类、标签等 */
    private Map<String, Object> metadata;
}