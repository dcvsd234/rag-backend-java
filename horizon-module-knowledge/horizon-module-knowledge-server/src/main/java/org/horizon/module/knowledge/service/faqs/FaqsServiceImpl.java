package org.horizon.module.knowledge.service.faqs;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.knowledge.controller.admin.faqs.vo.*;
import org.horizon.module.knowledge.dal.dataobject.faqs.FaqsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.knowledge.dal.mapper.faqs.FaqsMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FaqsServiceImpl implements FaqsService {

    @Resource
    private FaqsMapper faqsMapper;

    @Override
    public Long createFaqs(FaqsSaveReqVO createReqVO) {
        // 插入
        FaqsDO faqs = BeanUtils.toBean(createReqVO, FaqsDO.class);
        faqsMapper.insert(faqs);

        // 返回
        return faqs.getId();
    }

    /**
     * 批量插入 FAQ
     *
     * @param faqDOList FAQ 列表
     * @return 插入条数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBatch(List<FaqsDO> faqDOList) {
        if (CollUtil.isEmpty(faqDOList)) {
            return 0;
        }
        return faqsMapper.insertBatch(faqDOList);
    }

    @Override
    public void updateFaqs(FaqsSaveReqVO updateReqVO) {
        // 校验存在
        validateFaqsExists(updateReqVO.getId());
        // 更新
        FaqsDO updateObj = BeanUtils.toBean(updateReqVO, FaqsDO.class);
        faqsMapper.updateById(updateObj);
    }

    @Override
    public void deleteFaqs(Long id) {
        // 校验存在
        validateFaqsExists(id);
        // 删除
        faqsMapper.deleteById(id);
    }

    @Override
    public void deleteFaqsListByIds(List<Long> ids) {
        // 删除
        faqsMapper.deleteByIds(ids);
    }


    private void validateFaqsExists(Long id) {
        if (faqsMapper.selectById(id) == null) {
            throw exception(FAQS_NOT_EXISTS);
        }
    }

    @Override
    public FaqsDO getFaqs(Long id) {
        return faqsMapper.selectById(id);
    }

    @Override
    public PageResult<FaqsDO> getFaqsPage(FaqsPageReqVO pageReqVO) {
        return faqsMapper.selectPage(pageReqVO);
    }

}
