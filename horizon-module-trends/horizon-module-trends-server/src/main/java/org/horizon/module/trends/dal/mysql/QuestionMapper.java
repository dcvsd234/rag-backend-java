package org.horizon.module.trends.dal.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.horizon.module.trends.dal.dataobject.QuestionDO;

@Mapper
public interface QuestionMapper {
    int insert(QuestionDO question);
    QuestionDO selectById(@Param("id") Long questionId);
    // 如果有需要，也可加 update / delete 方法
}