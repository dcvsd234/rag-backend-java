package org.horizon.module.hotpoint.dal.dataobject;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Hotpoint Mapper
 *
 */
@Mapper
public interface HotpointMapper {

    /**
     * 插入问题
     */
    int insertQuestion(QuestionDO question);


}