package org.horizon.module.member.dal.mysql.config;

import org.horizon.framework.mybatis.core.mapper.BaseMapperX;
import org.horizon.module.member.dal.dataobject.config.MemberConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分设置 Mapper
 *
 * @author QingX
 */
@Mapper
public interface MemberConfigMapper extends BaseMapperX<MemberConfigDO> {
}
