package org.horizon.module.qa.service.useranswers;

import java.util.*;
import javax.validation.*;
import org.horizon.module.qa.controller.admin.useranswers.vo.*;
import org.horizon.module.qa.dal.dataobject.useranswers.UserAnswersDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG 用户回复表（保存终端用户每个提问对应的模型回复） Service 接口
 *
 * @author freedom
 */
public interface UserAnswersService {

    /**
     * 创建RAG 用户回复表（保存终端用户每个提问对应的模型回复）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserAnswers(@Valid UserAnswersSaveReqVO createReqVO);

    /**
     * 更新RAG 用户回复表（保存终端用户每个提问对应的模型回复）
     *
     * @param updateReqVO 更新信息
     */
    void updateUserAnswers(@Valid UserAnswersSaveReqVO updateReqVO);

    /**
     * 删除RAG 用户回复表（保存终端用户每个提问对应的模型回复）
     *
     * @param id 编号
     */
    void deleteUserAnswers(Long id);

    /**
     * 批量删除RAG 用户回复表（保存终端用户每个提问对应的模型回复）
     *
     * @param ids 编号
     */
    void deleteUserAnswersListByIds(List<Long> ids);

    /**
     * 获得RAG 用户回复表（保存终端用户每个提问对应的模型回复）
     *
     * @param id 编号
     * @return RAG 用户回复表（保存终端用户每个提问对应的模型回复）
     */
    UserAnswersDO getUserAnswers(Long id);

    /**
     * 获得RAG 用户回复表（保存终端用户每个提问对应的模型回复）分页
     *
     * @param pageReqVO 分页查询
     * @return RAG 用户回复表（保存终端用户每个提问对应的模型回复）分页
     */
    PageResult<UserAnswersDO> getUserAnswersPage(UserAnswersPageReqVO pageReqVO);

}