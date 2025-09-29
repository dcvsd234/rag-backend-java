package org.horizon.module.hotpoint.service;

import org.horizon.framework.common.util.object.BeanUtils;
import org.horizon.module.hotpoint.controller.admin.vo.questionKeyword.QuestionKeywordSaveReqVO;
import org.horizon.module.hotpoint.dal.dataobject.QuestionKeywordDO;
import org.horizon.module.hotpoint.dal.mysql.QuestionKeywordMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;


/**
 * hotpoint keyword Service 实现类
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