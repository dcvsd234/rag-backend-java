package org.horizon.framework.idempotent.config;

import org.horizon.framework.idempotent.core.aop.IdempotentAspect;
import org.horizon.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import org.horizon.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import org.horizon.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import org.horizon.framework.idempotent.core.keyresolver.impl.UserIdempotentKeyResolver;
import org.horizon.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.horizon.framework.redis.config.HorizonRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = HorizonRedisAutoConfiguration.class)
public class HorizonIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public UserIdempotentKeyResolver userIdempotentKeyResolver() {
        return new UserIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
