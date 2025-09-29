package org.horizon.module.knowledge.service.faqcategorylinks;

import java.util.*;
import javax.validation.*;
import org.horizon.module.knowledge.controller.admin.faqcategorylinks.vo.*;
import org.horizon.module.knowledge.dal.dataobject.faqcategorylinks.FaqCategoryLinksDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * FAQ分類关联 Service 接口
 *
 * @author freedoms
 */
public interface FaqCategoryLinksService {

    /**
     * 创建FAQ分類关联
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFaqCategoryLinks(@Valid FaqCategoryLinksSaveReqVO createReqVO);

    /**
     * 更新FAQ分類关联
     *
     * @param updateReqVO 更新信息
     */
    void updateFaqCategoryLinks(@Valid FaqCategoryLinksSaveReqVO updateReqVO);

    /**
     * 删除FAQ分類关联
     *
     * @param id 编号
     */
    void deleteFaqCategoryLinks(Long id);

    /**
     * 批量删除FAQ分類关联
     *
     * @param ids 编号
     */
    void deleteFaqCategoryLinksListByIds(List<Long> ids);

    /**
     * 获得FAQ分類关联
     *
     * @param id 编号
     * @return FAQ分類关联
     */
    FaqCategoryLinksDO getFaqCategoryLinks(Long id);

    /**
     * 获得FAQ分類关联分页
     *
     * @param pageReqVO 分页查询
     * @return FAQ分類关联分页
     */
    PageResult<FaqCategoryLinksDO> getFaqCategoryLinksPage(FaqCategoryLinksPageReqVO pageReqVO);

    /**
     * 批量插入 FAQ分類关联
     *
     * @param list FAQ分類关联集合
     */
    void insertBatch(@Valid List<FaqCategoryLinksDO> list);

}
