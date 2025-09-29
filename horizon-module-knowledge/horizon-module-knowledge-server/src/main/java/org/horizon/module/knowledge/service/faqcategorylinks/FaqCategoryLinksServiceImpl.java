package org.horizon.module.knowledge.service.faqcategorylinks;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.knowledge.controller.admin.faqcategorylinks.vo.*;
import org.horizon.module.knowledge.dal.dataobject.faqcategorylinks.FaqCategoryLinksDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.knowledge.dal.mapper.faqcategorylinks.FaqCategoryLinksMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * FAQ分類关联 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FaqCategoryLinksServiceImpl implements FaqCategoryLinksService {

    @Resource
    private FaqCategoryLinksMapper faqCategoryLinksMapper;

    @Override
    public Long createFaqCategoryLinks(FaqCategoryLinksSaveReqVO createReqVO) {
        // 插入
        FaqCategoryLinksDO faqCategoryLinks = BeanUtils.toBean(createReqVO, FaqCategoryLinksDO.class);
        faqCategoryLinksMapper.insert(faqCategoryLinks);

        // 返回
        return faqCategoryLinks.getId();
    }

    @Override
    public void updateFaqCategoryLinks(FaqCategoryLinksSaveReqVO updateReqVO) {
        // 校验存在
        validateFaqCategoryLinksExists(updateReqVO.getId());
        // 更新
        FaqCategoryLinksDO updateObj = BeanUtils.toBean(updateReqVO, FaqCategoryLinksDO.class);
        faqCategoryLinksMapper.updateById(updateObj);
    }

    @Override
    public void deleteFaqCategoryLinks(Long id) {
        // 校验存在
        validateFaqCategoryLinksExists(id);
        // 删除
        faqCategoryLinksMapper.deleteById(id);
    }

    @Override
    public void deleteFaqCategoryLinksListByIds(List<Long> ids) {
        // 删除
        faqCategoryLinksMapper.deleteByIds(ids);
    }


    private void validateFaqCategoryLinksExists(Long id) {
        if (faqCategoryLinksMapper.selectById(id) == null) {
            throw exception(FAQ_CATEGORY_LINKS_NOT_EXISTS);
        }
    }

    @Override
    public FaqCategoryLinksDO getFaqCategoryLinks(Long id) {
        return faqCategoryLinksMapper.selectById(id);
    }

    @Override
    public PageResult<FaqCategoryLinksDO> getFaqCategoryLinksPage(FaqCategoryLinksPageReqVO pageReqVO) {
        return faqCategoryLinksMapper.selectPage(pageReqVO);
    }

    /**
     * 批量插入 FAQ分類关联
     *
     * @param list FAQ分类关联集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<FaqCategoryLinksDO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        faqCategoryLinksMapper.insertBatch(list);
    }

}