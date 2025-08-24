package org.horizon.module.system.controller.admin.socail;

import org.horizon.framework.common.enums.UserTypeEnum;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.system.controller.admin.socail.vo.user.SocialUserBindReqVO;
import org.horizon.module.system.controller.admin.socail.vo.user.SocialUserPageReqVO;
import org.horizon.module.system.controller.admin.socail.vo.user.SocialUserRespVO;
import org.horizon.module.system.controller.admin.socail.vo.user.SocialUserUnbindReqVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import static org.horizon.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 社交用户（占位版）
 * 说明：
 * 1) 为了去除中国三方登录的强依赖，本控制器保留接口占位，但功能全部“失效化”；
 * 2) 统一返回空/false，后续接入 GitHub/Google/Twitter 等再恢复实现。
 */
@Tag(name = "管理后台 - 社交用户（占位）")
@RestController
@RequestMapping("/system/social-user")
@Validated
@Deprecated // 标记为占位
public class SocialUserController {

    @PostMapping("/bind")
    @Operation(summary = "社交绑定（占位，未启用）")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid SocialUserBindReqVO reqVO) {
        // 占位：不执行业务
        return success(false);
    }

    @DeleteMapping("/unbind")
    @Operation(summary = "取消社交绑定（占位，未启用）")
    public CommonResult<Boolean> socialUnbind(@RequestBody SocialUserUnbindReqVO reqVO) {
        // 占位：不执行业务
        return success(false);
    }

    @GetMapping("/get-bind-list")
    @Operation(summary = "获得绑定社交用户列表（占位，未启用）")
    public CommonResult<List<SocialUserRespVO>> getBindSocialUserList() {
        // 占位：返回空列表，避免编译期/运行期依赖
        return success(Collections.emptyList());
    }

    // ==================== 社交用户 CRUD（占位） ====================

    @GetMapping("/get")
    @Operation(summary = "获得社交用户（占位，未启用）")
    public CommonResult<SocialUserRespVO> getSocialUser(@RequestParam("id") Long id) {
        // 占位：返回空对象
        return success(new SocialUserRespVO());
    }

    @GetMapping("/page")
    @Operation(summary = "获得社交用户分页（占位，未启用）")
    public CommonResult<PageResult<SocialUserRespVO>> getSocialUserPage(@Valid SocialUserPageReqVO pageVO) {
        // 占位：返回空分页
        return success(new PageResult<>(Collections.emptyList(), 0L));
    }

}