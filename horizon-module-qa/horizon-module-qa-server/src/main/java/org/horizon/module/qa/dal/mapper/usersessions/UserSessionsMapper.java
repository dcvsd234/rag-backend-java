package org.horizon.module.qa.dal.mapper.usersessions;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.qa.controller.admin.usersessions.vo.UserSessionsPageReqVO;
import org.horizon.module.qa.dal.dataobject.usersessions.UserSessionsDO;

import java.util.List;

@Mapper
public interface UserSessionsMapper {

    // ====== CRUD ======
    int insert(UserSessionsDO entity);

    int updateById(UserSessionsDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    UserSessionsDO selectByAnonUserId(@Param("anonUserId") String anonUserId);

    UserSessionsDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<UserSessionsDO> selectPageList(UserSessionsPageReqVO reqVO);

    Long selectPageCount(UserSessionsPageReqVO reqVO);

    // ====== 统一分页方法：返回 PageResult ======
    default PageResult<UserSessionsDO> selectPage(UserSessionsPageReqVO reqVO) {
        List<UserSessionsDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }
}