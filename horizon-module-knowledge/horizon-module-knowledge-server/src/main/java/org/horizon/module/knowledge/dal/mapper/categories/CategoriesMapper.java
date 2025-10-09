package org.horizon.module.knowledge.dal.mapper.categories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.knowledge.controller.admin.categories.vo.CategoriesPageReqVO;
import org.horizon.module.knowledge.dal.dataobject.categories.CategoriesDO;

import java.util.List;

@Mapper
public interface CategoriesMapper {

    // ====== CRUD ======
    int insert(CategoriesDO entity);

    int updateById(CategoriesDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    CategoriesDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<CategoriesDO> selectPageList(CategoriesPageReqVO reqVO);

    Long selectPageCount(CategoriesPageReqVO reqVO);

    // 对 Service 暴露的统一分页方法：返回 PageResult
    default PageResult<CategoriesDO> selectPage(CategoriesPageReqVO reqVO) {
        List<CategoriesDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }

    // ====== 批量插入 ======
    int insertBatch(@Param("list") List<CategoriesDO> list);

    /**
     * 批量插入（若已存在则忽略，不回填 id）
     * 对应 SQL 使用 INSERT ... ON CONFLICT DO NOTHING
     */
    int insertBatchIgnore(@Param("list") List<CategoriesDO> list);

    /**
     * 批量查询已有或刚插入的分类 ID
     *
     * @param langId 分类语言
     * @param names  分类名集合
     * @return 已存在的分类对象（只保证 id 和 question 有效）
     */
    List<CategoriesDO> selectIdsByKeys(@Param("langId") String langId,
                                       @Param("names") List<String> names);
}