package org.horizon.module.knowledge.api.doc.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Schema(description = "RPC - 文档与 FAQ 提交状态响应 DTO")
@Data
@Accessors(chain = true) // 开启链式调用
public class UploadStatusRespDTO implements Serializable {

    @Schema(description = "是否成功")
    private Boolean success;

    @Schema(description = "消息")
    private String message;

    @Schema(description = "任务/批次 ID（可选）")
    private String taskId;

    @Schema(description = "文件 ID", example = "file-123")
    private String fileId;

    @Schema(description = "FAQ 数量", example = "5")
    private Integer faqCount;

    // ===== 静态工厂方法 =====
    public static UploadStatusRespDTO success(String message) {
        return new UploadStatusRespDTO()
                .setSuccess(true)
                .setMessage(message);
    }

    public static UploadStatusRespDTO error(String message) {
        return new UploadStatusRespDTO()
                .setSuccess(false)
                .setMessage(message);
    }

    // ===== 链式附加方法 =====
    public UploadStatusRespDTO withTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public UploadStatusRespDTO withFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public UploadStatusRespDTO withFaqCount(Integer count) {
        this.faqCount = count;
        return this;
    }
}