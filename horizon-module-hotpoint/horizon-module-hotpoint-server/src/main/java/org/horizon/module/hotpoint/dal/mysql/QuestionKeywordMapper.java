package org.horizon.module.hotpoint.dal.mysql;


import org.horizon.module.hotpoint.dal.dataobject.QuestionDO;
import org.horizon.module.hotpoint.dal.dataobject.QuestionKeywordDO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface QuestionKeywordMapper {
    int insert(QuestionKeywordDO qk);
    // 如有批量，可以加批量插入方法
}