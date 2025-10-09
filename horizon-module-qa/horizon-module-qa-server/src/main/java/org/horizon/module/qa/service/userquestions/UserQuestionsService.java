package org.horizon.module.qa.service.userquestions;

import java.util.*;
import javax.validation.*;
import org.horizon.module.qa.controller.admin.userquestions.vo.*;
import org.horizon.module.qa.dal.dataobject.userquestions.UserQuestionsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG 用户问题表（终端用户的每一次提问记录） Service 接口
 *
 * @author 芋道源码
 */
public interface UserQuestionsService {

    /**
     * 创建RAG 用户问题表（终端用户的每一次提问记录）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserQuestions(@Valid UserQuestionsSaveReqVO createReqVO);

    /**
     * 更新RAG 用户问题表（终端用户的每一次提问记录）
     *
     * @param updateReqVO 更新信息
     */
    void updateUserQuestions(@Valid UserQuestionsSaveReqVO updateReqVO);

    /**
     * 删除RAG 用户问题表（终端用户的每一次提问记录）
     *
     * @param id 编号
     */
    void deleteUserQuestions(Long id);

    /**
     * 批量删除RAG 用户问题表（终端用户的每一次提问记录）
     *
     * @param ids 编号
     */
    void deleteUserQuestionsListByIds(List<Long> ids);

    /**
     * 获得RAG 用户问题表（终端用户的每一次提问记录）
     *
     * @param id 编号
     * @return RAG 用户问题表（终端用户的每一次提问记录）
     */
    UserQuestionsDO getUserQuestions(Long id);

    /**
     * 获得RAG 用户问题表（终端用户的每一次提问记录）分页
     *
     * @param pageReqVO 分页查询
     * @return RAG 用户问题表（终端用户的每一次提问记录）分页
     */
    PageResult<UserQuestionsDO> getUserQuestionsPage(UserQuestionsPageReqVO pageReqVO);

}