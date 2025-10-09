package org.horizon.module.knowledge.service.faqembeddings;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.knowledge.controller.admin.faqembeddings.vo.*;
import org.horizon.module.knowledge.dal.dataobject.faqembeddings.FaqEmbeddingsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.knowledge.dal.mapper.faqembeddings.FaqEmbeddingsMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * FAQ 向量テーブル（FAQ 文本のベクトル表現を保存） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FaqEmbeddingsServiceImpl implements FaqEmbeddingsService {

    @Resource
    private FaqEmbeddingsMapper faqEmbeddingsMapper;

    @Override
    public Long createFaqEmbeddings(FaqEmbeddingsSaveReqVO createReqVO) {
        // 插入
        FaqEmbeddingsDO faqEmbeddings = BeanUtils.toBean(createReqVO, FaqEmbeddingsDO.class);
        faqEmbeddingsMapper.insert(faqEmbeddings);
        // 返回
        return faqEmbeddings.getId();
    }

    @Override
    public void updateFaqEmbeddings(FaqEmbeddingsSaveReqVO updateReqVO) {
        // 校验存在
        validateFaqEmbeddingsExists(updateReqVO.getId());
        // 更新
        FaqEmbeddingsDO updateObj = BeanUtils.toBean(updateReqVO, FaqEmbeddingsDO.class);
        faqEmbeddingsMapper.updateById(updateObj);
    }

    @Override
    public void deleteFaqEmbeddings(Long id) {
        // 校验存在
        validateFaqEmbeddingsExists(id);
        // 删除
        faqEmbeddingsMapper.deleteById(id);
    }

    @Override
    public void deleteFaqEmbeddingsListByIds(List<Long> ids) {
        // 删除
        faqEmbeddingsMapper.deleteByIds(ids);
    }

    private void validateFaqEmbeddingsExists(Long id) {
        if (faqEmbeddingsMapper.selectById(id) == null) {
            throw exception(FAQ_EMBEDDINGS_NOT_EXISTS);
        }
    }

    @Override
    public FaqEmbeddingsDO getFaqEmbeddings(Long id) {
        return faqEmbeddingsMapper.selectById(id);
    }

    @Override
    public PageResult<FaqEmbeddingsDO> getFaqEmbeddingsPage(FaqEmbeddingsPageReqVO pageReqVO) {
        return faqEmbeddingsMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<FaqEmbeddingsDO> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        faqEmbeddingsMapper.insertBatch(list);
    }
}