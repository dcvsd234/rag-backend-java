package org.horizon.module.trends.service;

import org.horizon.module.trends.controller.admin.vo.question.QuestionSaveReqVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * RAG 知识库节点 Service 接口
 */
public interface QuestionService {

    /**
     * 创建问题
     * @param createReqVO 创建信息
     * @return 节点 ID
     */
    Long create(@Valid @NotNull QuestionSaveReqVO createReqVO);

}