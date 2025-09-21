package org.horizon.framework.ai.core.dto.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.horizon.framework.ai.core.dto.base.BaseReq;

/**
 * File Ingestor 请求 DTO
 * - 对应 Python 端的 SliceRequest
 * - 用于请求文件切片（S3 对象存储）
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileIngestorReq extends BaseReq {

    /** 必填：对象存储的 bucket 名 */
    private String bucket;

    /** 必填：对象路径 key，例如 "tokyo/docs/law2024.pdf" */
    private String key;
}