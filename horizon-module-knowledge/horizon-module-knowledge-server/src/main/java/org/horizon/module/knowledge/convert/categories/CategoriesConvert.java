package org.horizon.module.knowledge.convert.categories;

import org.horizon.module.knowledge.dal.dataobject.categories.CategoriesDO;

/**
 * Categories 转换器
 * - 提供构造 CategoriesDO 的便捷方法
 * - 集中默认值管理
 */
public class CategoriesConvert {

    private CategoriesConvert() {
        // 工具类不允许实例化
    }

    /**
     * 小工厂方法：根据参数构建一个 CategoriesDO
     *
     * @param faqId       FAQ 主表 ID（仅用于临时存储，真正入库 categories 表时不使用）
     * @param name        分类名称
     * @param langId      语言 ID，例如 "ja"
     * @param displayFlag 显示标志，例如 "public"
     * @param operator    当前操作人（作为 creator/updater）
     * @return 构造好的 CategoriesDO 对象
     */
    public static CategoriesDO buildCategoryDO(Long faqId,
                                               String name,
                                               String langId,
                                               String displayFlag,
                                               String operator) {
        CategoriesDO c = new CategoriesDO();
        c.setFaqId(faqId);          // 临时字段，用于后续建立 link
        c.setQuestion(name);        // ⚠️ 如果实体里真正的字段是 name/label，请替换为 c.setName(name)
        c.setLangId(langId);
        c.setDisplayFlag(displayFlag);
        c.setCreator(operator);
        c.setUpdater(operator);
        c.setDeleted(false);
        return c;
    }
}