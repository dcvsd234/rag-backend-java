package org.horizon.module.dataset.framework.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.storage")
public class StorageProps {
    /** 文件客户端配置编号 */
    private Long configId = 1L;

    /** 本地存储根目录（直接来自 application.yml） */
    private String basePath;

    /** 对外域名 */
    private String domain;
}