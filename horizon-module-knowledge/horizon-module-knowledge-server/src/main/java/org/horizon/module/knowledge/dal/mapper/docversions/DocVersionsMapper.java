package org.horizon.module.knowledge.dal.mapper.docversions;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.module.knowledge.controller.admin.docversions.vo.DocVersionsPageReqVO;
import org.horizon.module.knowledge.dal.dataobject.docversions.DocVersionsDO;

import java.util.List;

@Mapper
public interface DocVersionsMapper {

    // ====== CRUD ======
    int insert(DocVersionsDO entity);

    int updateById(DocVersionsDO entity);

    int updateKnowledgeDocsIdById(DocVersionsDO entity);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    DocVersionsDO selectById(@Param("id") Long id);

    // ====== 分页：两条 SQL（列表 + 统计） ======
    List<DocVersionsDO> selectPageList(DocVersionsPageReqVO reqVO);

    Long selectPageCount(DocVersionsPageReqVO reqVO);

    // 统一给 Service 用的分页方法（保持你现有 Service 签名不变）
    default PageResult<DocVersionsDO> selectPage(DocVersionsPageReqVO reqVO) {
        List<DocVersionsDO> list = selectPageList(reqVO);
        Long total = selectPageCount(reqVO);
        return new PageResult<>(list, total == null ? 0L : total);
    }
}
