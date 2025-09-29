package org.horizon.module.knowledge.service.categoryembeddings;

import java.util.*;
import javax.validation.*;
import org.horizon.module.knowledge.controller.admin.categoryembeddings.vo.*;
import org.horizon.module.knowledge.dal.dataobject.categoryembeddings.CategoryEmbeddingsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG FAQ 分类向量 Service 接口
 *
 * @author 芋道源码
 */
public interface CategoryEmbeddingsService {

    /**
     * 创建RAG FAQ 分类向量
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCategoryEmbeddings(@Valid CategoryEmbeddingsSaveReqVO createReqVO);

    /**
     * 更新RAG FAQ 分类向量
     *
     * @param updateReqVO 更新信息
     */
    void updateCategoryEmbeddings(@Valid CategoryEmbeddingsSaveReqVO updateReqVO);

    /**
     * 删除RAG FAQ 分类向量
     *
     * @param id 编号
     */
    void deleteCategoryEmbeddings(Long id);

    /**
     * 批量删除RAG FAQ 分类向量
     *
     * @param ids 编号
     */
    void deleteCategoryEmbeddingsListByIds(List<Long> ids);

    /**
     * 获得RAG FAQ 分类向量
     *
     * @param id 编号
     * @return RAG FAQ 分类向量
     */
    CategoryEmbeddingsDO getCategoryEmbeddings(Long id);

    /**
     * 获得RAG FAQ 分类向量分页
     *
     * @param pageReqVO 分页查询
     * @return RAG FAQ 分类向量分页
     */
    PageResult<CategoryEmbeddingsDO> getCategoryEmbeddingsPage(CategoryEmbeddingsPageReqVO pageReqVO);

    /**
     * 批量插入RAG FAQ 分类向量
     *
     * @param list 分类向量列表
     */
    void insertBatch(@Valid List<CategoryEmbeddingsDO> list);

}
