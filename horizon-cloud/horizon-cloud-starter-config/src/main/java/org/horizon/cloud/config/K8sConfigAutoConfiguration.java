package org.horizon.cloud.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * 自动装配：
 * - 仅当 horizon.config.enabled=true（默认 true）时生效
 * - 依赖 spring-cloud-k8s-config 提供的 PropertySource / 刷新能力
 */
@AutoConfiguration
@EnableConfigurationProperties(HorizonConfigProperties.class)
@ConditionalOnProperty(prefix = "horizon.config", name = "enabled", havingValue = "true", matchIfMissing = true)
public class K8sConfigAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ConfigClient horizonConfigClient(Environment env) {
        return new DefaultConfigClient(env);
    }
}