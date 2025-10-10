package org.horizon.module.qa.service.usersessions;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.qa.controller.admin.usersessions.vo.*;
import org.horizon.module.qa.dal.dataobject.usersessions.UserSessionsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.qa.dal.mapper.usersessions.UserSessionsMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.qa.enums.ErrorCodeConstants.*;

/**
 * RAG 会话表：一段连续对话的容器（多个问题与回答的集合） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserSessionsServiceImpl implements UserSessionsService {

    @Resource
    private UserSessionsMapper userSessionsMapper;

    @Override
    public Long createUserSessions(UserSessionsSaveReqVO createReqVO) {
        // 插入
        UserSessionsDO userSessions = BeanUtils.toBean(createReqVO, UserSessionsDO.class);
        userSessions.setDeleted(false);
        userSessionsMapper.insert(userSessions);

        // 返回
        return userSessions.getId();
    }

    @Override
    public Long createUserSessions(UserSessionsDO userSessionsDO) {

        userSessionsDO.setDeleted(false);
        userSessionsMapper.insert(userSessionsDO);

        // 返回
        return userSessionsDO.getId();
    }

    @Override
    public void updateUserSessions(UserSessionsSaveReqVO updateReqVO) {
        // 校验存在
        validateUserSessionsExists(updateReqVO.getId());
        // 更新
        UserSessionsDO updateObj = BeanUtils.toBean(updateReqVO, UserSessionsDO.class);
        userSessionsMapper.updateById(updateObj);
    }

    @Override
    public void updateUserSessions(UserSessionsDO updateReqDO) {

        userSessionsMapper.updateById(updateReqDO);
    }

    @Override
    public void deleteUserSessions(Long id) {
        // 校验存在
        validateUserSessionsExists(id);
        // 删除
        userSessionsMapper.deleteById(id);
    }

    @Override
    public void deleteUserSessionsListByIds(List<Long> ids) {
        // 删除
        userSessionsMapper.deleteByIds(ids);
    }


    private void validateUserSessionsExists(Long id) {
        if (userSessionsMapper.selectById(id) == null) {
            throw exception(USER_SESSIONS_NOT_EXISTS);
        }
    }

    @Override
    public UserSessionsDO getUserSessions(Long id) {
        return userSessionsMapper.selectById(id);
    }

    @Override
    public UserSessionsDO getUserSessionsId(String id) {
        return userSessionsMapper.selectByAnonUserId(id);
    }


    @Override
    public PageResult<UserSessionsDO> getUserSessionsPage(UserSessionsPageReqVO pageReqVO) {
        return userSessionsMapper.selectPage(pageReqVO);
    }

}