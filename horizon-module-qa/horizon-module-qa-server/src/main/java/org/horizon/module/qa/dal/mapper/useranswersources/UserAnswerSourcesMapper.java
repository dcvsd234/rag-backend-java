package org.horizon.module.qa.dal.mapper.useranswersources;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.qa.controller.admin.useranswersources.vo.UserAnswerSourcesPageReqVO;
import org.horizon.module.qa.dal.dataobject.useranswersources.UserAnswerSourcesDO;

import java.util.List;

@Mapper
public interface UserAnswerSourcesMapper {

    // ====== CRUD ======
    int insert(UserAnswerSourcesDO entity);

    int updateById(UserAnswerSourcesDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    UserAnswerSourcesDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<UserAnswerSourcesDO> selectPageList(UserAnswerSourcesPageReqVO reqVO);

    Long selectPageCount(UserAnswerSourcesPageReqVO reqVO);

    // 对 Service 暴露的统一分页方法：返回 PageResult
    default PageResult<UserAnswerSourcesDO> selectPage(UserAnswerSourcesPageReqVO reqVO) {
        List<UserAnswerSourcesDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }
}