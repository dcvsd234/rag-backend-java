package org.horizon.module.hotpoint.service.hotpoint;

import org.horizon.module.hotpoint.controller.admin.vo.QuestionSaveReqVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * RAG 知识库节点 Service 接口
 */
public interface HotpointService {

    /**
     * 创建问题
     * @param createReqVO 创建信息
     * @return 节点 ID
     */
    Long createQuestion(@Valid @NotNull QuestionSaveReqVO createReqVO);

}