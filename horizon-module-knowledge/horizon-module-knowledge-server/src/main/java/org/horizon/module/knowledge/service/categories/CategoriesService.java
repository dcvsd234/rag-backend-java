package org.horizon.module.knowledge.service.categories;

import java.util.*;
import javax.validation.*;
import org.horizon.module.knowledge.controller.admin.categories.vo.*;
import org.horizon.module.knowledge.dal.dataobject.categories.CategoriesDO;
import org.horizon.framework.common.pojo.PageResult;

public interface CategoriesService {

    Long createCategories(@Valid CategoriesSaveReqVO createReqVO);

    void updateCategories(@Valid CategoriesSaveReqVO updateReqVO);

    void deleteCategories(Long id);

    void deleteCategoriesListByIds(List<Long> ids);

    CategoriesDO getCategories(Long id);

    PageResult<CategoriesDO> getCategoriesPage(CategoriesPageReqVO pageReqVO);

    /**
     * 批量“去重插入”分类，并回填 ID。
     * @param langId     语言ID（唯一键的一部分）
     * @param candidates 待处理的分类候选（question 必填）
     * @param operator   操作者（用于 creator/updater）
     */
    List<CategoriesDO> upsertAndFillIds(String langId,
                                        @Valid List<CategoriesDO> candidates,
                                        String operator);
}