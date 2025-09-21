package org.horizon.module.knowledge.api.vo.doc;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data

public class SubmitDocReq {

    @NotEmpty
    private String tenantId;

    @NotEmpty
    private String leafId;

    @Valid @NotNull
    private DocumentReq document;

    @Valid
    private List<PackReq> packs;

    @Data
    public static class DocumentReq {
        private String id;            // fileId（由上传接口返回）
        @NotEmpty
        private String fileName;
        private String mimeType;
        private Integer length;
        private Integer pagesCount;
    }

    @Data
    public static class PackReq {
        private Integer idx;
        private String id;
        private String question;
        private List<String> categories;
        private Integer evCount;
        private String status;
    }
}