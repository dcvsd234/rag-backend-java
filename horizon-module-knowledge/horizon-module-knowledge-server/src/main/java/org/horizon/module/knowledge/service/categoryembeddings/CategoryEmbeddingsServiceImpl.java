package org.horizon.module.knowledge.service.categoryembeddings;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.knowledge.controller.admin.categoryembeddings.vo.*;
import org.horizon.module.knowledge.dal.dataobject.categoryembeddings.CategoryEmbeddingsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.knowledge.dal.mapper.categoryembeddings.CategoryEmbeddingsMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * RAG FAQ 分类向量 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CategoryEmbeddingsServiceImpl implements CategoryEmbeddingsService {

    @Resource
    private CategoryEmbeddingsMapper categoryEmbeddingsMapper;

    @Override
    public Long createCategoryEmbeddings(CategoryEmbeddingsSaveReqVO createReqVO) {
        // 插入
        CategoryEmbeddingsDO categoryEmbeddings = BeanUtils.toBean(createReqVO, CategoryEmbeddingsDO.class);
        categoryEmbeddingsMapper.insert(categoryEmbeddings);

        // 返回
        return categoryEmbeddings.getId();
    }

    @Override
    public void updateCategoryEmbeddings(CategoryEmbeddingsSaveReqVO updateReqVO) {
        // 校验存在
        validateCategoryEmbeddingsExists(updateReqVO.getId());
        // 更新
        CategoryEmbeddingsDO updateObj = BeanUtils.toBean(updateReqVO, CategoryEmbeddingsDO.class);
        categoryEmbeddingsMapper.updateById(updateObj);
    }

    @Override
    public void deleteCategoryEmbeddings(Long id) {
        // 校验存在
        validateCategoryEmbeddingsExists(id);
        // 删除
        categoryEmbeddingsMapper.deleteById(id);
    }

    @Override
    public void deleteCategoryEmbeddingsListByIds(List<Long> ids) {
        // 删除
        categoryEmbeddingsMapper.deleteByIds(ids);
    }


    private void validateCategoryEmbeddingsExists(Long id) {
        if (categoryEmbeddingsMapper.selectById(id) == null) {
            throw exception(CATEGORY_EMBEDDINGS_NOT_EXISTS);
        }
    }

    @Override
    public CategoryEmbeddingsDO getCategoryEmbeddings(Long id) {
        return categoryEmbeddingsMapper.selectById(id);
    }

    @Override
    public PageResult<CategoryEmbeddingsDO> getCategoryEmbeddingsPage(CategoryEmbeddingsPageReqVO pageReqVO) {
        return categoryEmbeddingsMapper.selectPage(pageReqVO);
    }

    /**
     * 批量插入分类向量
     *
     * @param list 分类向量 DO 列表
     */
    @Override
    @Transactional
    public void insertBatch(List<CategoryEmbeddingsDO> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        categoryEmbeddingsMapper.insertBatch(list);
    }

}
