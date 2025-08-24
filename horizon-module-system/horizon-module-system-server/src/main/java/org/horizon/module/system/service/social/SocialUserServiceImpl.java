package org.horizon.module.system.service.social;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import org.horizon.framework.common.exception.ServiceException;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.system.api.social.dto.SocialUserBindReqDTO;
import org.horizon.module.system.api.social.dto.SocialUserRespDTO;
import org.horizon.module.system.controller.admin.socail.vo.user.SocialUserPageReqVO;
import org.horizon.module.system.dal.dataobject.social.SocialUserBindDO;
import org.horizon.module.system.dal.dataobject.social.SocialUserDO;
import org.horizon.module.system.dal.mysql.social.SocialUserBindMapper;
import org.horizon.module.system.dal.mysql.social.SocialUserMapper;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertSet;
import static org.horizon.framework.common.util.json.JsonUtils.toJsonString;
import static org.horizon.module.system.enums.ErrorCodeConstants.SOCIAL_USER_NOT_FOUND;

/**
 * 通用社交用户 Service 实现类
 * - 已去除微信/中国社交媒体强依赖
 * - 保留通用社交账号绑定、解绑、查询逻辑
 *
 * @author Horizon
 */
@Service
@Validated
@Slf4j
public class SocialUserServiceImpl implements SocialUserService {

    @Resource
    private SocialUserBindMapper socialUserBindMapper;
    @Resource
    private SocialUserMapper socialUserMapper;

    @Resource
    private SocialClientService socialClientService;

    @Override
    public List<SocialUserDO> getSocialUserList(Long userId, Integer userType) {
        List<SocialUserBindDO> binds = socialUserBindMapper.selectListByUserIdAndUserType(userId, userType);
        if (CollUtil.isEmpty(binds)) {
            return Collections.emptyList();
        }
        return socialUserMapper.selectByIds(convertSet(binds, SocialUserBindDO::getSocialUserId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String bindSocialUser(SocialUserBindReqDTO reqDTO) {
        // 通过授权码获取社交用户
        SocialUserDO socialUser = authSocialUser(reqDTO.getProviderType(), reqDTO.getUserType(),
                reqDTO.getCode(), reqDTO.getState());
        Assert.notNull(socialUser, "社交用户不能为空");

        // 解绑旧的绑定关系
        socialUserBindMapper.deleteByUserTypeAndSocialUserId(reqDTO.getUserType(), socialUser.getId());
        socialUserBindMapper.deleteByUserTypeAndUserIdAndSocialType(reqDTO.getUserType(),
                reqDTO.getUserId(), socialUser.getType());

        // 新增绑定
        SocialUserBindDO bind = SocialUserBindDO.builder()
                .userId(reqDTO.getUserId())
                .userType(reqDTO.getUserType())
                .socialUserId(socialUser.getId())
                .socialType(socialUser.getType())
                .build();
        socialUserBindMapper.insert(bind);
        return socialUser.getExternalId(); // 使用 externalId 代替 openid
    }

    @Override
    public void unbindSocialUser(Long userId, Integer userType, Integer providerType, String externalId) {
        SocialUserDO socialUser = socialUserMapper.selectByTypeAndExternalId(providerType, externalId);
        if (socialUser == null) {
            throw exception(SOCIAL_USER_NOT_FOUND);
        }
        socialUserBindMapper.deleteByUserTypeAndUserIdAndSocialType(userType, userId, socialUser.getType());
    }

    @Override
    public SocialUserRespDTO getSocialUserByUserId(Integer userType, Long userId, Integer providerType) {
        SocialUserBindDO bind = socialUserBindMapper.selectByUserIdAndUserTypeAndSocialType(userId, userType, providerType);
        if (bind == null) {
            return null;
        }
        SocialUserDO socialUser = socialUserMapper.selectById(bind.getSocialUserId());
        Assert.notNull(socialUser, "社交用户不能为空");
        return new SocialUserRespDTO(
                socialUser.getExternalId(),
                socialUser.getNickname(),
                socialUser.getAvatar(),
                bind.getUserId()
        );
    }

    @Override
    public SocialUserRespDTO getSocialUserByCode(Integer userType, Integer providerType, String code, String state) {
        SocialUserDO socialUser = authSocialUser(providerType, userType, code, state);
        Assert.notNull(socialUser, "社交用户不能为空");

        SocialUserBindDO bind = socialUserBindMapper.selectByUserTypeAndSocialUserId(userType, socialUser.getId());
        return new SocialUserRespDTO(
                socialUser.getExternalId(),
                socialUser.getNickname(),
                socialUser.getAvatar(),
                bind != null ? bind.getUserId() : null
        );
    }

    /**
     * 授权获得社交用户（通用 OAuth2）
     */
    @NotNull
    public SocialUserDO authSocialUser(Integer providerType, Integer userType, String code, String state) {
        // 从 DB 查询
        SocialUserDO socialUser = socialUserMapper.selectByTypeAndCodeAndState(providerType, code, state);
        if (socialUser != null) {
            return socialUser;
        }

        // 调用社交客户端（GitHub, Google, Twitter, etc.）
        AuthUser authUser = socialClientService.getAuthUser(providerType, userType, code, state);
        Assert.notNull(authUser, "第三方用户不能为空");

        // 保存或更新
        //socialUser = socialUserMapper.selectByTypeAndExternalId(providerType, authUser.getUuid());
        if (socialUser == null) {
            socialUser = new SocialUserDO();
        }
        socialUser.setType(providerType)
                .setCode(code).setState(state)
                .setExternalId(authUser.getUuid())
                .setToken(authUser.getToken().getAccessToken())
                .setRawTokenInfo(toJsonString(authUser.getToken()))
                .setNickname(authUser.getNickname())
                .setAvatar(authUser.getAvatar())
                .setRawUserInfo(toJsonString(authUser.getRawUserInfo()));
        if (socialUser.getId() == null) {
            socialUserMapper.insert(socialUser);
        } else {
            socialUserMapper.updateById(socialUser);
        }
        return socialUser;
    }

    // ==================== CRUD ====================

    @Override
    public SocialUserDO getSocialUser(Long id) {
        return socialUserMapper.selectById(id);
    }

    @Override
    public PageResult<SocialUserDO> getSocialUserPage(SocialUserPageReqVO pageReqVO) {
        return socialUserMapper.selectPage(pageReqVO);
    }

}