package org.horizon.module.knowledge.api.vo.doc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class FileUploadResp {
    private String fileId;   // 保存到库后返回的主键
    private String url;      // 访问URL
    private String path;     // 存储key（相对路径）
    private String fileName;
    private Long size;
    private String mimeType;
}