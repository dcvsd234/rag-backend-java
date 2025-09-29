package org.horizon.module.knowledge.dal.mapper.faqembeddings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.knowledge.controller.admin.faqembeddings.vo.FaqEmbeddingsPageReqVO;
import org.horizon.module.knowledge.dal.dataobject.faqembeddings.FaqEmbeddingsDO;

import java.util.List;

@Mapper
public interface FaqEmbeddingsMapper {

    // ====== CRUD ======
    int insert(FaqEmbeddingsDO entity);

    int updateById(FaqEmbeddingsDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    FaqEmbeddingsDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<FaqEmbeddingsDO> selectPageList(FaqEmbeddingsPageReqVO reqVO);

    Long selectPageCount(FaqEmbeddingsPageReqVO reqVO);

    // 对 Service 暴露的统一分页方法：返回 PageResult
    default PageResult<FaqEmbeddingsDO> selectPage(FaqEmbeddingsPageReqVO reqVO) {
        List<FaqEmbeddingsDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }

    int insertBatch(@Param("list") List<FaqEmbeddingsDO> list);
}