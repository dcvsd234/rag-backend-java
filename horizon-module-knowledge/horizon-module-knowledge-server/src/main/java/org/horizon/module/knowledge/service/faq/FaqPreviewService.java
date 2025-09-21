package org.horizon.module.knowledge.service.faq;

import org.horizon.framework.ai.core.dto.generate.GenerateReq;
import org.horizon.framework.ai.core.dto.generate.GenerateResp;
import org.horizon.framework.common.pojo.CommonResult;

/**
 * FAQ 预览 Service
 *
 * - 构建预览结果（不落库）
 * - 调用 AI 模型生成回答
 */
public interface FaqPreviewService {

    CommonResult<GenerateResp> preview(GenerateReq req);

}