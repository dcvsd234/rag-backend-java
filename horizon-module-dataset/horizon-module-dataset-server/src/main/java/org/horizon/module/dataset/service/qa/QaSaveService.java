package org.horizon.module.dataset.service.qa;

import lombok.extern.slf4j.Slf4j;
import org.horizon.module.dataset.api.enums.DocSourceType;
import org.horizon.module.dataset.api.vo.common.IdResp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 【伪码】QA 保存流程：
 * 1) 保存 QA 卡片
 * 2) 保存证据句
 * 3) （可选）保存原始文档并触发文档入库（切片 + 向量）
 * 4) 返回保存后的 QA 卡片 ID
 *
 * 说明：
 * - 为避免缺少 Mapper Bean 导致启动报错，这里先不注入任何依赖；
 * - 所有持久化/调用逻辑以 TODO 注释形式保留，后续按实际 Mapper 与 Service 回填。
 */
@Slf4j
@Service
public class QaSaveService {

    /**
     * 保存 QA（伪码）
     */
    @Transactional(rollbackFor = Exception.class)
    public IdResp saveQa(
            String tenantId,
            String question,
            String template,
            String audience,
            String previewText,
            List<String> evidences,
            String docTitle,
            String docContent,
            DocSourceType sourceType
    ) {
        log.info("📝 Save QA | tenantId={}, audience={}, evidences={}",
                tenantId, audience, evidences == null ? 0 : evidences.size());

        // ========== 1) 保存 QA 卡片 ==========
        // TODO: 构造 QaCardDO，设置字段（tenantId/question/template/audience/previewText/status 等）
        // TODO: 调用 qaCardMapper.insert(card) 获取自增 ID
        Long cardId = 0L; // 占位：替换为插入后返回的真实 ID

        // ========== 2) 保存证据句（可选）==========
        // TODO: 遍历 evidences，构造 QaEvidenceDO（tenantId/cardId/seq/text 等），批量或逐条插入

        // ========== 3) 可选：保存原始文档 & 触发入库 ==========
        // if (docContent != null && !docContent.isBlank()) {
        //   3.1) TODO: 保存 QaDocDO（tenantId/title/sourceType/rawContent 等）
        //   3.2) TODO: 绑定 QA 与 Doc（可在卡片存 docId 或建中间表）
        //   3.3) TODO: 调用文档入库服务（切片 + 向量），例如 docIngestService.ingestText(docContent, sourceType)
        // }

        log.info("✅ Save QA done | cardId={}", cardId);
        return new IdResp(cardId);
    }

    // ========== Mapper 合同（待 DAL 层实现后再恢复注入）==========
    // public interface QaCardMapper { void insert(QaCardDO entity); void bindDoc(Long cardId, Long docId); }
    // public interface QaEvidenceMapper { void insert(QaEvidenceDO entity); }
    // public interface QaDocMapper { void insert(QaDocDO entity); }
}