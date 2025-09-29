package org.horizon.module.knowledge.convert.faqcategorylinks;

import org.horizon.module.knowledge.dal.dataobject.categories.CategoriesDO;
import org.horizon.module.knowledge.dal.dataobject.faqcategorylinks.FaqCategoryLinksDO;

import java.util.ArrayList;
import java.util.List;

/**
 * FAQ分类关联 Convert
 *
 * 把 CategoriesDO 列表（带 faqId、id=categoryId）转换为
 * FaqCategoryLinksDO 列表，方便批量插入到 knowledge_faq_category_links。
 *
 * @author freedom
 */
public class FaqCategoryLinksConvert {

    private FaqCategoryLinksConvert() {
    }

    /**
     * 批量转换
     *
     * @param tenantId   租户ID
     * @param candidates 分类候选（要求包含 faqId 与 id(categoryId)）
     * @param operator   操作者
     * @return 关系DO列表
     */
    public static List<FaqCategoryLinksDO> toLinks(Long tenantId,
                                                   List<CategoriesDO> candidates,
                                                   String operator) {
        if (candidates == null || candidates.isEmpty()) {
            return List.of();
        }

        List<FaqCategoryLinksDO> list = new ArrayList<>();
        for (CategoriesDO c : candidates) {
            if (c.getId() == null || c.getFaqId() == null) {
                continue; // categoryId 或 faqId 不存在，跳过
            }

            FaqCategoryLinksDO link = FaqCategoryLinksDO.builder()
                    .id(null) // 自增
                    .tenantId(tenantId)
                    .faqId(c.getFaqId())
                    .categoryId(c.getId())
                    .confidence(1.0) // 默认人工建立=1.0
                    .linkSource("manual")
                    .build();

            link.setCreator(operator);
            link.setUpdater(operator);
            link.setDeleted(false);

            list.add(link);
        }
        return list;
    }
}