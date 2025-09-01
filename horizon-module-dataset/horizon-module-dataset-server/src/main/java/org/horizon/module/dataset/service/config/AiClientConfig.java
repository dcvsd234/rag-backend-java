package org.horizon.module.dataset.service.config;

import org.horizon.framework.ai.core.client.ModelToolsClient;
import org.horizon.framework.ai.properties.HorizonAiProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * AI 客户端配置
 * - 提供 aiRestTemplate 与 ModelToolsClient Bean
 */
@AutoConfiguration
@EnableConfigurationProperties(HorizonAiProperties.class)
public class AiClientConfig {

    @Bean("aiRestTemplate")
    @ConditionalOnMissingBean(name = "aiRestTemplate")
    public RestTemplate aiRestTemplate(RestTemplateBuilder builder, HorizonAiProperties p) {
        return builder
                .setConnectTimeout(Duration.ofMillis(p.getConnectTimeoutMs()))
                .setReadTimeout(Duration.ofMillis(p.getReadTimeoutMs()))
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public ModelToolsClient modelToolsClient(RestTemplate aiRestTemplate, HorizonAiProperties props) {
        return new ModelToolsClient(aiRestTemplate, props.getBaseUrl(), props.getApiKey());
    }
}