package org.horizon.module.system.service.social;

import org.horizon.framework.test.core.ut.BaseDbUnitTest;
import org.junit.jupiter.api.Disabled;
import org.springframework.context.annotation.Import;

/**
 * 占位测试：SocialClientServiceImpl
 * 说明：由于已移除/停用中国第三方登录相关能力，本测试暂时禁用。
 * 后续如接入新的第三方（GitHub/Google等），再恢复或重写单测。
 */
@Disabled("Temporarily disabled: CN social features are removed")
@Import(SocialClientServiceImpl.class)
public class SocialClientServiceImplTest extends BaseDbUnitTest {
    // 保留为空的占位类，避免未来恢复时重新建文件
}