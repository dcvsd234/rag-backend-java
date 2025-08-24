package org.horizon.module.system.service.social;

import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.system.controller.admin.socail.vo.client.SocialClientPageReqVO;
import org.horizon.module.system.controller.admin.socail.vo.client.SocialClientSaveReqVO;
import org.horizon.module.system.dal.dataobject.social.SocialClientDO;
import me.zhyd.oauth.model.AuthUser;

import javax.validation.Valid;
import java.util.List;

/**
 * 社交应用 Service 接口（精简版）
 * 已移除微信/中国社交媒体相关接口，仅保留基础占位，方便未来扩展
 *
 * @author Horizon
 */
public interface SocialClientService {

    /**
     * 获得社交平台的授权 URL（占位）
     *
     * @param socialType  社交平台的类型
     * @param userType    用户类型
     * @param redirectUri 重定向 URL
     * @return 授权 URL（目前返回空/默认值）
     */
    String getAuthorizeUrl(Integer socialType, Integer userType, String redirectUri);

    /**
     * 请求社交平台，获得授权的用户（占位）
     *
     * @param socialType 社交平台的类型
     * @param userType   用户类型
     * @param code       授权码
     * @param state      授权 state
     * @return 授权用户（目前返回 null）
     */
    AuthUser getAuthUser(Integer socialType, Integer userType, String code, String state);

    // =================== 客户端管理 ===================

    /**
     * 创建社交客户端
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSocialClient(@Valid SocialClientSaveReqVO createReqVO);

    /**
     * 更新社交客户端
     *
     * @param updateReqVO 更新信息
     */
    void updateSocialClient(@Valid SocialClientSaveReqVO updateReqVO);

    /**
     * 删除社交客户端
     *
     * @param id 编号
     */
    void deleteSocialClient(Long id);

    /**
     * 批量删除社交客户端
     *
     * @param ids 编号数组
     */
    void deleteSocialClientList(List<Long> ids);

    /**
     * 获得社交客户端
     *
     * @param id 编号
     * @return 社交客户端
     */
    SocialClientDO getSocialClient(Long id);

    /**
     * 获得社交客户端分页
     *
     * @param pageReqVO 分页查询
     * @return 社交客户端分页
     */
    PageResult<SocialClientDO> getSocialClientPage(SocialClientPageReqVO pageReqVO);

}