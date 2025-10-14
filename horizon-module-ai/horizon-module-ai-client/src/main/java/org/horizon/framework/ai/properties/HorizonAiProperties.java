package org.horizon.framework.ai.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Horizon AI 客户端配置
 * 从配置文件中读取 horizon.ai.* 相关参数
 */
@Data
@Validated
@ConfigurationProperties(prefix = "horizon.ai")
public class HorizonAiProperties {

    /**
     * model_tools 对外统一地址（网关/内网LB/ServiceName）
     * 示例: http://127.0.0.1:8020
     */
    private String baseUrl;

    /**
     * 鉴权 API Key（可选，如果服务端需要认证则填写）
     */
    private String apiKey;

    /**
     * 连接超时时间 (毫秒)
     */
    private int connectTimeoutMs = 3000;

    /**
     * 读取超时时间 (毫秒)
     */
    private int readTimeoutMs = 30000;

    /**
     * 写入超时时间 (毫秒)
     */
    private int writeTimeoutMs = 30000;

    /**
     * WebClient 内存最大缓存 (MB)
     */
    private int maxInMemoryMb = 16;
}