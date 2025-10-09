package org.horizon.framework.test.core;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.horizon.framework.datasource.config.HorizonDataSourceAutoConfiguration;
import org.horizon.framework.mybatis.config.HorizonMybatisAutoConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * 连接远程 PostgreSQL 的集成测试基类（只在 profile=it-remote 下启用）
 * - 不使用 H2 / 不跑 SQL init
 * - 默认 @Transactional 测试用例回滚，尽量不污染远端
 * - 连不通则 assume 跳过，避免干扰其他测试
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = BaseRemotePgIT.Application.class)
@ActiveProfiles("it-remote")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public abstract class BaseRemotePgIT {

    @Import({
            // 数据源
            HorizonDataSourceAutoConfiguration.class,
            DataSourceAutoConfiguration.class,
            DataSourceTransactionManagerAutoConfiguration.class,
            // MyBatis 配置
            HorizonMybatisAutoConfiguration.class,
            // 其它
            SpringUtil.class
    })
    public static class Application {}

    @BeforeAll
    static void checkConnection() {
        // 没有注入连接串就直接跳过（防误触）
        String url = System.getProperty("REMOTE_DB_URL", System.getenv("REMOTE_DB_URL"));
        assumeTrue(StrUtil.isNotBlank(url), "REMOTE_DB_URL 未设置，跳过远程集成测试。");
    }

    @BeforeAll
    static void ping(DataSource dataSource) {
        try (Connection c = dataSource.getConnection()) {
            assumeTrue(c.isValid(3), "无法连接远程数据库，跳过集成测试。");
        } catch (SQLException e) {
            assumeTrue(false, "连接远程数据库失败，跳过集成测试：" + e.getMessage());
        }
    }
}
