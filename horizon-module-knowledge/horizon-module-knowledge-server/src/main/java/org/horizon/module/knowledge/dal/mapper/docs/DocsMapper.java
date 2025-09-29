package org.horizon.module.knowledge.dal.mapper.docs;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.module.knowledge.dal.dataobject.docs.DocsDO;
import org.horizon.module.knowledge.controller.admin.docs.vo.DocsPageReqVO;
import org.horizon.framework.common.pojo.PageResult;

import java.util.List;

@Mapper
public interface DocsMapper {

    int insert(DocsDO docs);

    int updateById(DocsDO docs);

    int deleteById(@Param("id") Long id);

    int deleteByIds(@Param("ids") List<Long> ids);

    DocsDO selectById(@Param("id") Long id);

    List<DocsDO> selectList(@Param("tenantId") Long tenantId);

    List<DocsDO> selectPage(DocsPageReqVO reqVO);
}