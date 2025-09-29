package org.horizon.module.knowledge.dal.mapper.faqcategorylinks;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.knowledge.controller.admin.faqcategorylinks.vo.FaqCategoryLinksPageReqVO;
import org.horizon.module.knowledge.dal.dataobject.faqcategorylinks.FaqCategoryLinksDO;

import java.util.List;

@Mapper
public interface FaqCategoryLinksMapper {

    // ====== CRUD ======
    int insert(FaqCategoryLinksDO entity);

    int updateById(FaqCategoryLinksDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    FaqCategoryLinksDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<FaqCategoryLinksDO> selectPageList(FaqCategoryLinksPageReqVO reqVO);

    Long selectPageCount(FaqCategoryLinksPageReqVO reqVO);

    // 对 Service 暴露的统一分页方法：返回 PageResult
    default PageResult<FaqCategoryLinksDO> selectPage(FaqCategoryLinksPageReqVO reqVO) {
        List<FaqCategoryLinksDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }
    // ====== 批量插入 ======
    int insertBatch(@Param("list") List<FaqCategoryLinksDO> list);
}