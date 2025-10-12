package org.horizon.framework.ai.config;


import lombok.RequiredArgsConstructor;
import org.horizon.framework.ai.core.client.ModelToolsClient;
import org.horizon.framework.ai.properties.HorizonAiProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@AutoConfiguration
@EnableConfigurationProperties(HorizonAiProperties.class)
@RequiredArgsConstructor
public class AiClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "aiRestTemplate")
    public RestTemplate aiRestTemplate(RestTemplateBuilder builder, HorizonAiProperties p) {
        // Boot 会自动提供 RestTemplateBuilder；若没有，也可以 new RestTemplate()
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