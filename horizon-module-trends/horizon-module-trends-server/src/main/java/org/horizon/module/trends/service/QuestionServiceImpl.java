package org.horizon.module.trends.service;

import org.horizon.framework.common.util.object.BeanUtils;
import org.horizon.module.trends.controller.admin.vo.question.QuestionSaveReqVO;
import org.horizon.module.trends.dal.dataobject.QuestionDO;
import org.horizon.module.trends.dal.mysql.QuestionMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;


/**
 * trends question Service 实现类
 *
 */
@Service
@Validated
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public Long create(QuestionSaveReqVO createReqVO) {
        QuestionDO question = BeanUtils.toBean(createReqVO, QuestionDO.class);
        questionMapper.insert(question);
        return question.getId();
    }
}