package org.horizon.module.system.service.social;

import org.horizon.framework.common.exception.ServiceException;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.system.api.social.dto.SocialUserBindReqDTO;
import org.horizon.module.system.api.social.dto.SocialUserRespDTO;
import org.horizon.module.system.controller.admin.socail.vo.user.SocialUserPageReqVO;
import org.horizon.module.system.dal.dataobject.social.SocialUserDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 社交用户 Service 接口（精简版）
 * - 移除与微信/中国社交媒体的耦合，仅保留通用占位
 * - 以后可接入 GitHub/Google/Twitter/自建 OAuth2 Provider
 */
public interface SocialUserService {

    /** 获得指定用户的社交账号列表 */
    List<SocialUserDO> getSocialUserList(Long userId, Integer userType);

    /**
     * 绑定社交账号
     * @return 平台唯一标识（externalId / subject / handle 等）
     */
    String bindSocialUser(@Valid SocialUserBindReqDTO reqDTO);

    /**
     * 取消绑定社交账号
     * @param providerType 平台类型
     * @param externalId   平台唯一ID（替代 openid）
     */
    void unbindSocialUser(Long userId, Integer userType, Integer providerType, String externalId);

    /** 通过 userId 获取社交用户 */
    SocialUserRespDTO getSocialUserByUserId(Integer userType, Long userId, Integer providerType);

    /**
     * 通过授权码换社交用户；认证失败抛 {@link ServiceException}
     */
    SocialUserRespDTO getSocialUserByCode(Integer userType, Integer providerType, String code, String state);

    // ==================== CRUD ====================

    SocialUserDO getSocialUser(Long id);

    PageResult<SocialUserDO> getSocialUserPage(SocialUserPageReqVO pageReqVO);
}