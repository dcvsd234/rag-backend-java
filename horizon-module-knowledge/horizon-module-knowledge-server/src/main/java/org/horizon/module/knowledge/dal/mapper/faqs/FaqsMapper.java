package org.horizon.module.knowledge.dal.mapper.faqs;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.knowledge.controller.admin.faqs.vo.FaqsPageReqVO;
import org.horizon.module.knowledge.dal.dataobject.faqs.FaqsDO;

import java.util.List;

@Mapper
public interface FaqsMapper {

    // ====== CRUD ======
    int insert(FaqsDO entity);

    // ====== 批量插入 ======
    int insertBatch(@Param("list") List<FaqsDO> list);

    int updateById(FaqsDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    FaqsDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<FaqsDO> selectPageList(FaqsPageReqVO reqVO);

    Long selectPageCount(FaqsPageReqVO reqVO);

    // 对 Service 暴露的统一分页方法：返回 PageResult
    default PageResult<FaqsDO> selectPage(FaqsPageReqVO reqVO) {
        List<FaqsDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }
}
