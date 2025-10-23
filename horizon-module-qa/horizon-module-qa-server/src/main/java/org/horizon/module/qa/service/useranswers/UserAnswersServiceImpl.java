package org.horizon.module.qa.service.useranswers;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.qa.controller.admin.useranswers.vo.*;
import org.horizon.module.qa.dal.dataobject.useranswers.UserAnswersDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.qa.dal.mapper.useranswers.UserAnswersMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.qa.enums.ErrorCodeConstants.*;

/**
 * RAG 用户回复表（保存终端用户每个提问对应的模型回复） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserAnswersServiceImpl implements UserAnswersService {

    @Resource
    private UserAnswersMapper userAnswersMapper;

    @Override
    public Long createUserAnswers(UserAnswersSaveReqVO createReqVO) {
        // 插入
        UserAnswersDO userAnswers = BeanUtils.toBean(createReqVO, UserAnswersDO.class);
        userAnswersMapper.insert(userAnswers);

        // 返回
        return userAnswers.getId();
    }

    @Override
    public Long createUserAnswers(UserAnswersDO createReqDO) {
        // 插入
        userAnswersMapper.insert(createReqDO);

        // 返回
        return createReqDO.getId();
    }

    @Override
    public void updateUserAnswers(UserAnswersSaveReqVO updateReqVO) {
        // 校验存在
        validateUserAnswersExists(updateReqVO.getId());
        // 更新
        UserAnswersDO updateObj = BeanUtils.toBean(updateReqVO, UserAnswersDO.class);
        userAnswersMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserAnswers(Long id) {
        // 校验存在
        validateUserAnswersExists(id);
        // 删除
        userAnswersMapper.deleteById(id);
    }

    @Override
    public void deleteUserAnswersListByIds(List<Long> ids) {
        // 删除
        userAnswersMapper.deleteByIds(ids);
    }


    private void validateUserAnswersExists(Long id) {
        if (userAnswersMapper.selectById(id) == null) {
            throw exception(USER_ANSWERS_NOT_EXISTS);
        }
    }

    @Override
    public UserAnswersDO getUserAnswers(Long id) {
        return userAnswersMapper.selectById(id);
    }

    @Override
    public PageResult<UserAnswersDO> getUserAnswersPage(UserAnswersPageReqVO pageReqVO) {
        return userAnswersMapper.selectPage(pageReqVO);
    }

}