package org.horizon.module.knowledge.controller.admin.qa.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "文档与QA套件提交请求")
public class SubmitDocReq {

    @Schema(description = "租户ID", example = "t-001")
    @NotEmpty
    private String tenantId;

    @Schema(description = "目标叶子/分类ID（文件最终归属）", example = "leaf-labor")
    @NotEmpty
    private String leafId; // 只需传目标叶子，不用提交整棵树

    @Schema(description = "文档元数据")
    @Valid @NotNull
    private DocumentReq document;

    @Schema(description = "QA 套件列表")
    @Valid
    private List<PackReq> packs;

    // —— 子对象 —— //
    @Data
    @Schema(description = "提交的文档元信息")
    public static class DocumentReq {
        @Schema(description = "文档ID（若已有）", example = "doc-123")
        private String id; // 前端可能拿不到，可为空；后端入库后生成亦可

        @Schema(description = "文件名", example = "报告.xlsx")
        @NotEmpty
        private String fileName;

        @Schema(description = "MIME 类型", example = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        private String mimeType;

        @Schema(description = "正文长度（字符数）", example = "2038")
        private Integer length;

        @Schema(description = "页数", example = "0")
        private Integer pagesCount;
    }

    @Data
    @Schema(description = "QA 套件")
    public static class PackReq {
        @Schema(description = "序号（从1开始）", example = "1")
        private Integer idx;

        @Schema(description = "套件ID（前端草稿ID，可选）", example = "79545e5e-9429-419a-bc73-4a3afeed7536")
        private String id;

        @Schema(description = "问题文本")
        private String question;

        @Schema(description = "分类标签", example = "[\"policy\",\"energy\"]")
        private List<String> categories;

        @Schema(description = "证据条数", example = "3")
        private Integer evCount;

        @Schema(description = "状态", example = "draft")
        private String status;

        // 如需提交证据详情，打开下面字段（前端也能很快对齐）
        // private List<EvidenceReq> evidences;
    }

    // 可选：若要提交证据详情解开注释
    // @Data
    // @Schema(description = "证据")
    // public static class EvidenceReq {
    //     @Schema(description = "证据文本")
    //     private String text;
    //     @Schema(description = "来源定位（页码/坐标/URL等）")
    //     private String source;
    // }
}