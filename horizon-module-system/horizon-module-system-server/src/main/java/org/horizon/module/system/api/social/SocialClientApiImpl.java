package org.horizon.module.system.api.social;

import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.system.api.social.dto.*;
import org.horizon.module.system.service.social.SocialClientService;
import org.horizon.module.system.service.social.SocialUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static org.horizon.framework.common.pojo.CommonResult.success;

/**
 * 社交应用的 API 实现类（占位精简版）
 * - 中国系（微信/小程序等）相关接口保留占位实现，不做实际调用，全部返回空/false
 * - 仅 getAuthorizeUrl 仍可正常使用（用于 GitHub/Google/Twitter 等通用 OAuth）
 */
@RestController
@Validated
@Slf4j
public class SocialClientApiImpl implements SocialClientApi {

    @Resource
    private SocialClientService socialClientService;
    @Resource
    private SocialUserService socialUserService;

    /** 保留通用授权跳转能力 */
    @Override
    public CommonResult<String> getAuthorizeUrl(Integer socialType, Integer userType, String redirectUri) {
        return success(socialClientService.getAuthorizeUrl(socialType, userType, redirectUri));
    }

    // -------------------- 以下为中国系能力的占位实现 --------------------

    /** 占位：微信公众号 JS-SDK 签名 */
    @Deprecated
    @Override
    public CommonResult<SocialWxJsapiSignatureRespDTO> createWxMpJsapiSignature(Integer userType, String url) {
        log.info("[createWxMpJsapiSignature] Feature disabled. Return placeholder.");
        // 返回一个空对象占位，避免前端 NPE（也可以直接返回 fail，看你接口约定）
        return success(new SocialWxJsapiSignatureRespDTO());
    }

    /** 占位：微信小程序手机号解析 */
    @Deprecated
    @Override
    public CommonResult<SocialWxPhoneNumberInfoRespDTO> getWxMaPhoneNumberInfo(Integer userType, String phoneCode) {
        log.info("[getWxMaPhoneNumberInfo] Feature disabled. Return placeholder.");
        return success(new SocialWxPhoneNumberInfoRespDTO());
    }

    /** 占位：获取小程序码 */
    @Deprecated
    @Override
    public CommonResult<byte[]> getWxaQrcode(SocialWxQrcodeReqDTO reqVO) {
        log.info("[getWxaQrcode] Feature disabled. Return placeholder.");
        return success(new byte[0]);
    }

    /** 占位：订阅消息模板列表 */
    @Deprecated
    @Override
    public CommonResult<List<SocialWxaSubscribeTemplateRespDTO>> getWxaSubscribeTemplateList(Integer userType) {
        log.info("[getWxaSubscribeTemplateList] Feature disabled. Return placeholder.");
        return success(Collections.emptyList());
    }

    /** 占位：发送订阅消息 */
    @Deprecated
    @Override
    public CommonResult<Boolean> sendWxaSubscribeMessage(SocialWxaSubscribeMessageSendReqDTO reqDTO) {
        log.info("[sendWxaSubscribeMessage] Feature disabled. Return false.");
        return success(false);
    }

    /** 占位：上传发货信息 */
    @Deprecated
    @Override
    public CommonResult<Boolean> uploadWxaOrderShippingInfo(Integer userType, SocialWxaOrderUploadShippingInfoReqDTO reqDTO) {
        log.info("[uploadWxaOrderShippingInfo] Feature disabled. Return false.");
        return success(false);
    }

    /** 占位：确认收货通知 */
    @Deprecated
    @Override
    public CommonResult<Boolean> notifyWxaOrderConfirmReceive(Integer userType, SocialWxaOrderNotifyConfirmReceiveReqDTO reqDTO) {
        log.info("[notifyWxaOrderConfirmReceive] Feature disabled. Return false.");
        return success(false);
    }
}