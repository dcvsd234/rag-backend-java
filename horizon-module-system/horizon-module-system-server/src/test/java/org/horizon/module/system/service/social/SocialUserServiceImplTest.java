package org.horizon.module.system.service.social;

import org.horizon.framework.test.core.ut.BaseDbUnitTest;
import org.junit.jupiter.api.Disabled;
import org.springframework.context.annotation.Import;

/**
 * 占位测试：SocialUserServiceImpl
 *
 * 说明：
 * - 原测试涉及中国第三方（Gitee / DingTalk / 微信等），已不适用。
 * - 暂时禁用，避免编译 & 测试失败。
 * - 未来如接入 GitHub / Google / Twitter 等，可恢复或重写单测。
 */
@Disabled("Temporarily disabled: CN social features are removed")
@Import(SocialUserServiceImpl.class)
public class SocialUserServiceImplTest extends BaseDbUnitTest {
    // 保留占位，避免未来恢复时重新建文件
}