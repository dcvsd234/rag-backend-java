package org.horizon.module.hotpoint.service.hotpoint;

import org.horizon.framework.common.util.object.BeanUtils;
import org.horizon.module.hotpoint.controller.admin.vo.QuestionSaveReqVO;
import org.horizon.module.hotpoint.dal.dataobject.HotpointMapper;
import org.horizon.module.hotpoint.dal.dataobject.QuestionDO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;


/**
 * hotpoint Service 实现类
 *
 */
@Service
@Validated
public class HotpointServiceImpl implements HotpointService {

    @Resource
    private HotpointMapper hotpointMapper;

    @Override
    public Long createQuestion(QuestionSaveReqVO createReqVO) {
        QuestionDO question = BeanUtils.toBean(createReqVO, QuestionDO.class);
        hotpointMapper.insertQuestion(question);
        return question.getId();
    }

}