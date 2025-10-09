package org.horizon.module.qa.service.useranswersources;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.qa.controller.admin.useranswersources.vo.*;
import org.horizon.module.qa.dal.dataobject.useranswersources.UserAnswerSourcesDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.qa.dal.mapper.useranswersources.UserAnswerSourcesMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.qa.enums.ErrorCodeConstants.*;

/**
 * RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserAnswerSourcesServiceImpl implements UserAnswerSourcesService {

    @Resource
    private UserAnswerSourcesMapper userAnswerSourcesMapper;

    @Override
    public Long createUserAnswerSources(UserAnswerSourcesSaveReqVO createReqVO) {
        // 插入
        UserAnswerSourcesDO userAnswerSources = BeanUtils.toBean(createReqVO, UserAnswerSourcesDO.class);
        userAnswerSourcesMapper.insert(userAnswerSources);

        // 返回
        return userAnswerSources.getId();
    }

    @Override
    public void updateUserAnswerSources(UserAnswerSourcesSaveReqVO updateReqVO) {
        // 校验存在
        validateUserAnswerSourcesExists(updateReqVO.getId());
        // 更新
        UserAnswerSourcesDO updateObj = BeanUtils.toBean(updateReqVO, UserAnswerSourcesDO.class);
        userAnswerSourcesMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserAnswerSources(Long id) {
        // 校验存在
        validateUserAnswerSourcesExists(id);
        // 删除
        userAnswerSourcesMapper.deleteById(id);
    }

    @Override
    public void deleteUserAnswerSourcesListByIds(List<Long> ids) {
        // 删除
        userAnswerSourcesMapper.deleteByIds(ids);
    }


    private void validateUserAnswerSourcesExists(Long id) {
        if (userAnswerSourcesMapper.selectById(id) == null) {
            throw exception(USER_ANSWER_SOURCES_NOT_EXISTS);
        }
    }

    @Override
    public UserAnswerSourcesDO getUserAnswerSources(Long id) {
        return userAnswerSourcesMapper.selectById(id);
    }

    @Override
    public PageResult<UserAnswerSourcesDO> getUserAnswerSourcesPage(UserAnswerSourcesPageReqVO pageReqVO) {
        return userAnswerSourcesMapper.selectPage(pageReqVO);
    }

}
