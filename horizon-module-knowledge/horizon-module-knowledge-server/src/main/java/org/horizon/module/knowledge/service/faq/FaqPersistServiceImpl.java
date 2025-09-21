package org.horizon.module.knowledge.service.faq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horizon.module.knowledge.api.doc.dto.DocumentDTO;
import org.horizon.module.knowledge.api.doc.dto.FaqDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * FAQ 持久化实现类
 * - 这里可以接数据库 / 向量库
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FaqPersistServiceImpl implements FaqPersistService {

    // private final FaqMapper faqMapper;   // TODO: MyBatis Mapper
    // private final VectorStore vectorStore; // TODO: 向量库

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFaqs(String tenantId, String leafId, DocumentDTO document, List<FaqDTO> faqs) {
        log.info("[saveFaqs] tenantId={}, leafId={}, docId={}, faqCount={}",
                tenantId, leafId, document.getId(), faqs.size());

        // TODO 1. 保存到数据库
        // for (FaqDTO faq : faqs) {
        //     faqMapper.insert(...);
        // }

        // TODO 2. 保存到向量库（可选）
        // vectorStore.add(document.getId(), faqs);

        log.info("[saveFaqs] 保存成功");
    }
}