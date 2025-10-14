package org.horizon.module.qa.dal.mapper.userquestions;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.qa.controller.admin.userquestions.vo.UserQuestionsPageReqVO;
import org.horizon.module.qa.dal.dataobject.userquestions.UserQuestionsDO;

import java.util.List;

@Mapper
public interface UserQuestionsMapper {

    // ====== CRUD ======
    int insert(UserQuestionsDO entity);

    int insertSelective(UserQuestionsDO entity);

    int updateById(UserQuestionsDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    UserQuestionsDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<UserQuestionsDO> selectPageList(UserQuestionsPageReqVO reqVO);

    Long selectPageCount(UserQuestionsPageReqVO reqVO);

    // 对 Service 暴露的统一分页方法：返回 PageResult
    default PageResult<UserQuestionsDO> selectPage(UserQuestionsPageReqVO reqVO) {
        List<UserQuestionsDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }
}