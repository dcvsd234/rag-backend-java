package org.horizon.module.qa.dal.mapper.useranswers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.qa.controller.admin.useranswers.vo.UserAnswersPageReqVO;
import org.horizon.module.qa.dal.dataobject.useranswers.UserAnswersDO;

import java.util.List;

@Mapper
public interface UserAnswersMapper {

    // ====== CRUD ======
    int insert(UserAnswersDO entity);

    int updateById(UserAnswersDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    UserAnswersDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<UserAnswersDO> selectPageList(UserAnswersPageReqVO reqVO);

    Long selectPageCount(UserAnswersPageReqVO reqVO);

    // ====== 统一分页返回 PageResult ======
    default PageResult<UserAnswersDO> selectPage(UserAnswersPageReqVO reqVO) {
        List<UserAnswersDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }
}