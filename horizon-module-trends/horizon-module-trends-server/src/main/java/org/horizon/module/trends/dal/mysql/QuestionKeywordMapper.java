package org.horizon.module.trends.dal.mysql;


import org.horizon.module.trends.dal.dataobject.QuestionKeywordDO;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface QuestionKeywordMapper {
    int insert(QuestionKeywordDO qk);
    // 如有批量，可以加批量插入方法
}