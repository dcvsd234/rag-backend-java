package org.horizon.module.knowledge.enums.message;

/**
 * Document source type.
 * Used to identify how a document is submitted.
 */
public enum DocSourceEnum {

    /** Uploaded directly from local file */
    UPLOAD,

    /** Provided by URL (crawler or remote fetch) */
    URL,

    /** Pasted text content */
    PASTE,

    /** From cloud object storage (e.g. OSS/S3) */
    OBJECT_STORAGE,

    /** Other custom sources */
    OTHER
}