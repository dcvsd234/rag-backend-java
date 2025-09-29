package org.horizon.module.knowledge.convert.faqs;

import cn.hutool.core.util.StrUtil;
import org.horizon.module.knowledge.api.doc.dto.FaqDTO;
import org.horizon.module.knowledge.dal.dataobject.faqs.FaqsDO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FaqConvert {

    private FaqConvert() {}

    /**
     * 将 List<FaqDTO> 转为 List<FaqsDO>
     *
     * @param dtos       FAQ DTO 列表（question/answer 必填）
     * @param nodeId     所属知识库节点 ID
     * @param tenantId   租户 ID
     * @param versionId  文档版本 ID
     * @param sourceType 来源（manual/doc/import/auto）
     * @param langId     语言（如 "ja", "en"）
     * @param operator   当前操作人
     */
    public static List<FaqsDO> toDOList(
            List<FaqDTO> dtos,
            Long nodeId,
            Long tenantId,
            Long versionId,
            String sourceType,
            String langId,
            String operator
    ) {
        if (dtos == null || dtos.isEmpty()) return List.of();

        final String st = StrUtil.blankToDefault(sourceType, "manual");
        final String lang = StrUtil.blankToDefault(langId, "ja");

        List<FaqsDO> list = new ArrayList<>(dtos.size());
        for (FaqDTO dto : dtos) {
            String q = StrUtil.trim(dto.getQuestion());
            String a = StrUtil.trim(dto.getAnswer());
            if (StrUtil.isBlank(q) || StrUtil.isBlank(a)) {
                // 跳过空问答，避免脏数据（也可选择抛异常）
                continue;
            }

            FaqsDO e = FaqsDO.builder()
                    .id(null) // 自增
                    .tenantId(tenantId)
                    .nodeId(nodeId)
                    .knowledgeDocVersionsId(versionId)
                    .sourceType(st)
                    .question(q)
                    .answer(a)
                    .langId(lang)
                    .displayFlag(mapStatus(dto.getStatus()))
                    .build();

            // 父类字段（BaseDO）用 setter 设置，避免 @Builder 不包含父类字段的问题
            e.setDeleted(false);
            e.setCreator(operator);
            e.setUpdater(operator);

            // ✅ 追加：把分类携带到 DO（要求 FaqsDO 中有 @TableField(exist = false) List<String> categories）
            if (dto.getCategories() != null && !dto.getCategories().isEmpty()) {
                List<String> cats = dto.getCategories().stream()
                        .filter(c -> c != null && !c.isBlank())
                        .map(String::trim)
                        .distinct()
                        .collect(Collectors.toList());
                if (!cats.isEmpty()) {
                    e.setCategories(cats);
                }
            }

            list.add(e);
        }
        return list;
    }

    /** 将 status 文本映射到 displayFlag */
    private static String mapStatus(String status) {
        if (status == null) return "pending";
        String s = status.trim().toLowerCase();
        return switch (s) {
            case "faq", "public", "publish" -> "public";
            case "draft", "internal" -> "internal";
            case "private" -> "private";
            case "archived", "archive" -> "archived";
            default -> "pending";
        };
    }

}