package org.horizon.module.knowledge.service.faq;

import org.horizon.module.knowledge.api.doc.dto.DocumentDTO;
import org.horizon.module.knowledge.api.doc.dto.FaqDTO;

import java.util.List;

/**
 * FAQ 持久化 Service
 *
 * 负责将 FAQ 数据保存到数据库 / 向量库，并与文档建立关联
 */
public interface FaqPersistService {

    /**
     * 保存 FAQ 列表
     *
     * @param tenantId 租户 ID
     * @param leafId   分类叶子 ID
     * @param document 文档信息
     * @param faqs     FAQ 列表
     */
    void saveFaqs(String tenantId, String leafId, DocumentDTO document, List<FaqDTO> faqs);

}