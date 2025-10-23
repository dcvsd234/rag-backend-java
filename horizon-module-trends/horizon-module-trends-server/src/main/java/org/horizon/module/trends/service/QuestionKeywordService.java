package org.horizon.module.trends.service;

import org.horizon.module.trends.controller.admin.vo.questionKeyword.QuestionKeywordSaveReqVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *trends question-keyword Service 接口
 */
public interface QuestionKeywordService {

    /**
     * 创建关键词
     * @param createReqVO 创建信息
     * @return 关键词 ID
     */
    Long create(@Valid @NotNull QuestionKeywordSaveReqVO createReqVO);

}