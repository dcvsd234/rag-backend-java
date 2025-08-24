package org.horizon.module.system.dal.mysql.social;

import org.apache.ibatis.annotations.Mapper;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.mybatis.core.mapper.BaseMapperX;
import org.horizon.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.horizon.module.system.controller.admin.socail.vo.user.SocialUserPageReqVO;
import org.horizon.module.system.dal.dataobject.social.SocialUserDO;

@Mapper
public interface SocialUserMapper extends BaseMapperX<SocialUserDO> {

    // 按类型 + code + state 查询（修正拼写 AndState）
    default SocialUserDO selectByTypeAndCodeAndState(Integer type, String code, String state) {
        return selectOne(SocialUserDO::getType, type,
                SocialUserDO::getCode, code,
                SocialUserDO::getState, state);
    }

    // 按类型 + externalId 查询（替代原来的 openid）
    default SocialUserDO selectByTypeAndExternalId(Integer type, String externalId) {
        return selectFirstOne(SocialUserDO::getType, type,
                SocialUserDO::getExternalId, externalId);
    }

    // 分页查询：DO 用 externalId；入参 VO 仍沿用原来的 getOpenid() 以减少改动
    default PageResult<SocialUserDO> selectPage(SocialUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SocialUserDO>()
                .eqIfPresent(SocialUserDO::getType, reqVO.getType())
                .likeIfPresent(SocialUserDO::getNickname, reqVO.getNickname())
                .likeIfPresent(SocialUserDO::getExternalId, reqVO.getOpenid()) // 这里映射到 externalId
                .betweenIfPresent(SocialUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SocialUserDO::getId));
    }
}