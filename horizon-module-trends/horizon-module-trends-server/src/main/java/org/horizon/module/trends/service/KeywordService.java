package org.horizon.module.trends.service;

import org.horizon.module.trends.controller.admin.vo.keyword.KeywordSaveReqVO;
import org.horizon.module.trends.controller.admin.vo.keyword.KeywordPageReqVO;
import org.horizon.module.trends.dal.dataobject.KeywordDO;

import org.horizon.framework.common.pojo.PageResult;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *trends Service 接口
 */
public interface KeywordService {
    /**
     * 获得关键词分页列表
     * @param reqVO 分页条件
     * @return 关键词分页列表
     */
    PageResult<KeywordDO> selectPage(KeywordPageReqVO reqVO);

}