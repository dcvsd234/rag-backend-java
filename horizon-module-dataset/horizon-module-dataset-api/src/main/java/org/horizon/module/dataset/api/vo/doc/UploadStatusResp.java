package org.horizon.module.dataset.api.vo.doc;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response object for document/file upload status.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadStatusResp {

    /** Upload status: SUCCESS / FAILED / PROCESSING */
    private String status;

    /** Uploaded document ID (if success) */
    private String docId;

    /** Original file name */
    private String fileName;

    /** File size in bytes */
    private Long fileSize;

    /** Error message (if failed) */
    private String errorMessage;
}