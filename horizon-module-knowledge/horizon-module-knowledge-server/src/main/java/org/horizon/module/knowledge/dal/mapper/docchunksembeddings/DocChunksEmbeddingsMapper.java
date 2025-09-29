package org.horizon.module.knowledge.dal.mapper.docchunksembeddings;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.knowledge.controller.admin.docchunksembeddings.vo.DocChunksEmbeddingsPageReqVO;
import org.horizon.module.knowledge.dal.dataobject.docchunks.DocChunksDO;
import org.horizon.module.knowledge.dal.dataobject.docchunksembeddings.DocChunksEmbeddingsDO;

import java.util.List;

@Mapper
public interface DocChunksEmbeddingsMapper {

    // ====== CRUD ======
    int insert(DocChunksEmbeddingsDO entity);

    int insertBatch(@Param("list") List<DocChunksEmbeddingsDO> list);

    int updateById(DocChunksEmbeddingsDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    DocChunksEmbeddingsDO selectById(@Param("id") Long id);

    // ====== 分页查询（两条 SQL：列表 + 统计） ======
    List<DocChunksEmbeddingsDO> selectPageList(DocChunksEmbeddingsPageReqVO reqVO);

    Long selectPageCount(DocChunksEmbeddingsPageReqVO reqVO);

    // 对 Service 暴露的统一分页方法：返回 PageResult
    default PageResult<DocChunksEmbeddingsDO> selectPage(DocChunksEmbeddingsPageReqVO reqVO) {
        List<DocChunksEmbeddingsDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }
}