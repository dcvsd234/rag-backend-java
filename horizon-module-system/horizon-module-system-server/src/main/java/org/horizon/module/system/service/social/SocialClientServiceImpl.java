package org.horizon.module.system.service.social;

import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.util.object.BeanUtils;
import org.horizon.module.system.controller.admin.socail.vo.client.SocialClientPageReqVO;
import org.horizon.module.system.controller.admin.socail.vo.client.SocialClientSaveReqVO;
import org.horizon.module.system.dal.dataobject.social.SocialClientDO;
import org.horizon.module.system.dal.mysql.social.SocialClientMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 社交应用 Service 实现类（精简版）
 * - 已移除微信/中国社交媒体的逻辑
 * - 保留最小化占位，方便未来扩展
 *
 * @author Horizon
 */
@Service
@Slf4j
public class SocialClientServiceImpl implements SocialClientService {

    @Resource
    private SocialClientMapper socialClientMapper;

    // =================== 基础占位 ===================

    @Override
    public String getAuthorizeUrl(Integer socialType, Integer userType, String redirectUri) {
        // 占位：未来扩展时替换成真实逻辑
        log.info("[getAuthorizeUrl] socialType={}, userType={}, redirectUri={}", socialType, userType, redirectUri);
        return redirectUri;
    }

    @Override
    public AuthUser getAuthUser(Integer socialType, Integer userType, String code, String state) {
        // 占位：未来扩展时替换成真实逻辑
        log.info("[getAuthUser] socialType={}, userType={}, code={}, state={}", socialType, userType, code, state);
        return null;
    }

    // =================== 客户端管理 ===================

    @Override
    public Long createSocialClient(@Valid SocialClientSaveReqVO createReqVO) {
        // 插入
        SocialClientDO client = BeanUtils.toBean(createReqVO, SocialClientDO.class);
        socialClientMapper.insert(client);
        return client.getId();
    }

    @Override
    public void updateSocialClient(@Valid SocialClientSaveReqVO updateReqVO) {
        // 更新
        SocialClientDO updateObj = BeanUtils.toBean(updateReqVO, SocialClientDO.class);
        socialClientMapper.updateById(updateObj);
    }

    @Override
    public void deleteSocialClient(Long id) {
        socialClientMapper.deleteById(id);
    }

    @Override
    public void deleteSocialClientList(List<Long> ids) {
        socialClientMapper.deleteByIds(ids);
    }

    @Override
    public SocialClientDO getSocialClient(Long id) {
        return socialClientMapper.selectById(id);
    }

    @Override
    public PageResult<SocialClientDO> getSocialClientPage(SocialClientPageReqVO pageReqVO) {
        return socialClientMapper.selectPage(pageReqVO);
    }

}