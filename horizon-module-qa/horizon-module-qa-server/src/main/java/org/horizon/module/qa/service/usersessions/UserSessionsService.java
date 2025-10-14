package org.horizon.module.qa.service.usersessions;

import java.util.*;
import javax.validation.*;
import org.horizon.module.qa.controller.admin.usersessions.vo.*;
import org.horizon.module.qa.dal.dataobject.usersessions.UserSessionsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG 会话表：一段连续对话的容器（多个问题与回答的集合） Service 接口
 *
 * @author freedom
 */
public interface UserSessionsService {

    /**
     * 创建RAG 会话表：一段连续对话的容器（多个问题与回答的集合）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserSessions(@Valid UserSessionsSaveReqVO createReqVO);

    Long createUserSessions(@Valid UserSessionsDO createReqDO);

    /**
     * 更新RAG 会话表：一段连续对话的容器（多个问题与回答的集合）
     *
     * @param updateReqVO 更新信息
     */
    void updateUserSessions(@Valid UserSessionsSaveReqVO updateReqVO);

    void updateUserSessions(@Valid UserSessionsDO updateReqDO);

    /**
     * 删除RAG 会话表：一段连续对话的容器（多个问题与回答的集合）
     *
     * @param id 编号
     */
    void deleteUserSessions(Long id);

    /**
     * 批量删除RAG 会话表：一段连续对话的容器（多个问题与回答的集合）
     *
     * @param ids 编号
     */
    void deleteUserSessionsListByIds(List<Long> ids);

    /**
     * 获得RAG 会话表：一段连续对话的容器（多个问题与回答的集合）
     *
     * @param id 编号
     * @return RAG 会话表：一段连续对话的容器（多个问题与回答的集合）
     */
    UserSessionsDO getUserSessions(Long id);

    UserSessionsDO getUserSessionsId(String id);

    /**
     * 获得RAG 会话表：一段连续对话的容器（多个问题与回答的集合）分页
     *
     * @param pageReqVO 分页查询
     * @return RAG 会话表：一段连续对话的容器（多个问题与回答的集合）分页
     */
    PageResult<UserSessionsDO> getUserSessionsPage(UserSessionsPageReqVO pageReqVO);

}