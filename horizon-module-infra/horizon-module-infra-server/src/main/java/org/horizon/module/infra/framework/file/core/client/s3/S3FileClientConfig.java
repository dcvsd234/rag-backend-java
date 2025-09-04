package org.horizon.module.infra.framework.file.core.client.s3;

import cn.hutool.core.util.StrUtil;
import org.horizon.module.infra.framework.file.core.client.FileClientConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

/**
 * S3 文件客户端的配置类
 *
 * @author 芋道源码
 */
@Data
public class S3FileClientConfig implements FileClientConfig {


    /**
     * 节点地址
     */
    @NotNull(message = "endpoint 不能为空")
    private String endpoint;
    /**
     * 自定义域名
     */
    @URL(message = "domain 必须是 URL 格式")
    private String domain;
    /**
     * 存储 Bucket
     */
    @NotNull(message = "bucket 不能为空")
    private String bucket;

    /**
     * 访问 Key
     */
    @NotNull(message = "accessKey 不能为空")
    private String accessKey;
    /**
     * 访问 Secret
     */
    @NotNull(message = "accessSecret 不能为空")
    private String accessSecret;

    /**
     * 是否启用 PathStyle 访问
     */
    @NotNull(message = "enablePathStyleAccess 不能为空")
    private Boolean enablePathStyleAccess;

    @SuppressWarnings("RedundantIfStatement")
    @AssertTrue(message = "domain 校验未通过")
    @JsonIgnore
    public boolean isDomainValid() {
        return true;
    }

}
