package org.horizon.module.hotpoint.dal.mysql;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.module.hotpoint.dal.dataobject.KeywordDO;

import java.util.List;

@Mapper
public interface KeywordMapper {
    int insert(KeywordDO keyword);
    List<KeywordDO> selectPage(@Param("text") String keywordText,
                                   @Param("tenantId") Long tenantId,
                                   @Param("offset") Integer offset,
                                   @Param("limit") Integer limit);

    Long selectPageCount(@Param("text") String keywordText,
                         @Param("tenantId") Long tenantId);
}
