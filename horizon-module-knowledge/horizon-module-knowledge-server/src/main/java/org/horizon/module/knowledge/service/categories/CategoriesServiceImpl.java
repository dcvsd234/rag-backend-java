package org.horizon.module.knowledge.service.categories;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import org.horizon.module.knowledge.controller.admin.categories.vo.CategoriesPageReqVO;
import org.horizon.module.knowledge.controller.admin.categories.vo.CategoriesSaveReqVO;
import org.horizon.module.knowledge.dal.dataobject.categories.CategoriesDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.util.object.BeanUtils;
import org.horizon.module.knowledge.dal.mapper.categories.CategoriesMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.CATEGORIES_NOT_EXISTS;

/**
 * RAG FAQ 分类 Service 实现类
 *
 * @author
 */
@Service
@Validated
public class CategoriesServiceImpl implements CategoriesService {

    @Resource
    private CategoriesMapper categoriesMapper;

    @Override
    public Long createCategories(CategoriesSaveReqVO createReqVO) {
        // 插入
        CategoriesDO categories = BeanUtils.toBean(createReqVO, CategoriesDO.class);
        categoriesMapper.insert(categories);
        return categories.getId();
    }

    @Override
    public void updateCategories(CategoriesSaveReqVO updateReqVO) {
        // 校验存在
        validateCategoriesExists(updateReqVO.getId());
        // 更新
        CategoriesDO updateObj = BeanUtils.toBean(updateReqVO, CategoriesDO.class);
        categoriesMapper.updateById(updateObj);
    }

    @Override
    public void deleteCategories(Long id) {
        // 校验存在
        validateCategoriesExists(id);
        categoriesMapper.deleteById(id);
    }

    @Override
    public void deleteCategoriesListByIds(List<Long> ids) {
        categoriesMapper.deleteByIds(ids);
    }

    private void validateCategoriesExists(Long id) {
        if (categoriesMapper.selectById(id) == null) {
            throw exception(CATEGORIES_NOT_EXISTS);
        }
    }

    @Override
    public CategoriesDO getCategories(Long id) {
        return categoriesMapper.selectById(id);
    }

    @Override
    public PageResult<CategoriesDO> getCategoriesPage(CategoriesPageReqVO pageReqVO) {
        return categoriesMapper.selectPage(pageReqVO);
    }

    /**
     * 批量“去重插入”分类（按 (lang_id, question) 唯一），并回填 ID。
     * 逻辑：
     *  1) 规范化候选项里的分类名（去空白/去重），得到 names；
     *  2) 按 (langId, names) 批量查询已存在的分类，记录已有集合；
     *  3) 对不存在的名称构造插入列表，执行 INSERT ... ON CONFLICT DO NOTHING；
     *  4) 再按 (langId, names) 查询一遍，把所有 ID 回填到 candidates；
     *
     * @param langId    语言ID（唯一键的一部分）
     * @param candidates 待处理的分类候选（question 必填；其它字段若未填，这里会给默认值）
     * @param operator  操作者（用于 creator/updater）
     * @return 已回填 id 的列表（与入参 candidates 一一对应；无效项会原样返回但 id 可能为 null）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CategoriesDO> upsertAndFillIds(String langId,
                                               List<CategoriesDO> candidates,
                                               String operator) {
        if (candidates == null || candidates.isEmpty()) return List.of();

        // 1) 规范化并提取名称去重
        List<String> names = candidates.stream()
                .map(CategoriesDO::getQuestion)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        if (names.isEmpty()) return candidates;

        // 2) 查询已存在的 (langId, question)
        List<CategoriesDO> existing = categoriesMapper.selectIdsByKeys(langId, names);
        Set<String> existNames = existing.stream()
                .map(CategoriesDO::getQuestion)
                .collect(Collectors.toSet());

        // 3) 组装需要插入的记录
        List<CategoriesDO> toInsert = new ArrayList<>();
        for (CategoriesDO c : candidates) {
            String name = c.getQuestion() == null ? null : c.getQuestion().trim();
            if (name == null || name.isEmpty()) {
                continue; // 跳过无效项
            }
            if (!existNames.contains(name)) {
                // 补默认字段
                c.setLangId(langId);
                if (c.getDisplayFlag() == null) c.setDisplayFlag("public");
                if (c.getDeleted() == null) c.setDeleted(false);
                c.setCreator(operator);
                c.setUpdater(operator);
                toInsert.add(c);
            }
        }
        if (!toInsert.isEmpty()) {
            // 依赖 XML 中的 INSERT ... ON CONFLICT DO NOTHING
            categoriesMapper.insertBatchIgnore(toInsert);
        }

        // 4) 再查一遍，建立 (question -> id) 映射，并把 id 回填到 candidates
        List<CategoriesDO> all = categoriesMapper.selectIdsByKeys(langId, names);
        Map<String, Long> nameToId = new HashMap<>(all.size() * 2);
        for (CategoriesDO row : all) {
            // question 在库内应为规范化后的值
            nameToId.put(row.getQuestion(), row.getId());
        }
        for (CategoriesDO c : candidates) {
            String name = c.getQuestion() == null ? null : c.getQuestion().trim();
            if (name != null && !name.isEmpty()) {
                Long id = nameToId.get(name);
                if (id != null) c.setId(id);
            }
        }
        return candidates;
    }
}