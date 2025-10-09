package org.horizon.module.knowledge.dal.mapper.categoryembeddings;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.knowledge.controller.admin.categoryembeddings.vo.CategoryEmbeddingsPageReqVO;
import org.horizon.module.knowledge.dal.dataobject.categoryembeddings.CategoryEmbeddingsDO;

import java.util.List;

@Mapper
public interface CategoryEmbeddingsMapper {

    // ====== CRUD ======
    int insert(CategoryEmbeddingsDO entity);

    int updateById(CategoryEmbeddingsDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    CategoryEmbeddingsDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<CategoryEmbeddingsDO> selectPageList(CategoryEmbeddingsPageReqVO reqVO);

    Long selectPageCount(CategoryEmbeddingsPageReqVO reqVO);

    // 对 Service 暴露的统一分页方法：返回 PageResult
    default PageResult<CategoryEmbeddingsDO> selectPage(CategoryEmbeddingsPageReqVO reqVO) {
        List<CategoryEmbeddingsDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }

    // ====== 批量插入 ======
    int insertBatch(@Param("list") List<CategoryEmbeddingsDO> list);
}