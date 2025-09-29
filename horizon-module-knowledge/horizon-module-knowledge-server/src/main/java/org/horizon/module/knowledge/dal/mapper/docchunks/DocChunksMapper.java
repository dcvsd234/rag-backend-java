package org.horizon.module.knowledge.dal.mapper.docchunks;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.knowledge.controller.admin.docchunks.vo.DocChunksPageReqVO;
import org.horizon.module.knowledge.dal.dataobject.docchunks.DocChunksDO;

import java.util.List;

@Mapper
public interface DocChunksMapper {

    // ====== CRUD ======
    int insert(DocChunksDO entity);


    int insertBatch(@Param("list") List<DocChunksDO> list);

    int updateById(DocChunksDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    DocChunksDO selectById(@Param("id") Long id);

    // ====== 分页（两条 SQL：列表 + 统计） ======
    List<DocChunksDO> selectPageList(DocChunksPageReqVO reqVO);

    Long selectPageCount(DocChunksPageReqVO reqVO);

    // 对 Service 暴露的统一分页方法：返回 PageResult
    default PageResult<DocChunksDO> selectPage(DocChunksPageReqVO reqVO) {
        List<DocChunksDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }
}