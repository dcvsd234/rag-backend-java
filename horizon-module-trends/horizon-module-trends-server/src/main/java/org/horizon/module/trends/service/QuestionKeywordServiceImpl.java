package org.horizon.module.trends.service;

import org.horizon.framework.common.util.object.BeanUtils;
import org.horizon.module.trends.controller.admin.vo.questionKeyword.QuestionKeywordSaveReqVO;
import org.horizon.module.trends.dal.dataobject.QuestionKeywordDO;
import org.horizon.module.trends.dal.mysql.QuestionKeywordMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;


/**
 * trends keyword Service 实现类
 *
 */
@Service
@Validated
public class QuestionKeywordServiceImpl implements QuestionKeywordService {

    @Resource
    private QuestionKeywordMapper questionKeywordMapper;

    @Override
    public Long create(QuestionKeywordSaveReqVO createReqVO) {
        QuestionKeywordDO questionKeyword = BeanUtils.toBean(createReqVO, QuestionKeywordDO.class);
        questionKeywordMapper.insert(questionKeyword);
        return questionKeyword.getId();
    }

}