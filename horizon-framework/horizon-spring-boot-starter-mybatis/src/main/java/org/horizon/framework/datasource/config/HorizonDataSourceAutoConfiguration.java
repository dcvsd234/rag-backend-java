package org.horizon.framework.datasource.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据库配置（HikariCP）
 * 说明：删除了 Druid 相关的过滤器与属性绑定，不再注册任何与 /druid 控制台相关的 Bean。
 * HikariCP 由 Spring Boot / dynamic-datasource 根据 yml 自动装配。
 */
@AutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true) // 启动事务管理
public class HorizonDataSourceAutoConfiguration {
    // 无需额外 Bean。Hikari 指标通过 Actuator 自动暴露（若引入 micrometer）。
}