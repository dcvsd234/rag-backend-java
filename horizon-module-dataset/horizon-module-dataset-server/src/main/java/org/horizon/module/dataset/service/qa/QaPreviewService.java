package org.horizon.module.dataset.service.qa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horizon.framework.ai.core.dto.generate.GenerateReq;
import org.horizon.framework.ai.core.dto.generate.GenerateResp;
import org.horizon.framework.common.pojo.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * QA Preview Service
 * - Build preview from user input (question / template / evidences)
 * - Call model_zoo/generator_service via /infer
 * - Return preview result to front-end (NOT persisted)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QaPreviewService {

    private final RestTemplate restTemplate;

    /** Prefer config: horizon.ai.base-url, fallback to local dev default */
    @Value("${horizon.ai.base-url:http://127.0.0.1:8020}")
    private String baseUrl;

    /**
     * Preview QA generation based on given request.
     */
    public CommonResult<GenerateResp> preview(GenerateReq req) {
        try {
            int evidenceCount = req.getTemplateVars() == null ? 0 : req.getTemplateVars().size();
            log.info("🔎 QA Preview start | question='{}', audience='{}', evidenceVars={}",
                    req.getInput(), req.getAudience(), evidenceCount);

            GenerateResp resp = restTemplate.postForObject(
                    baseUrl + "/infer",
                    req,
                    GenerateResp.class
            );

            if (resp == null) {
                log.warn("⚠️ Preview result is null");
                return CommonResult.error(500, "Preview failed: empty response");
            }

            log.info("✅ QA Preview done | textPreview={}", resp.getText());
            return CommonResult.success(resp);

        } catch (Exception e) {
            log.error("❌ QA Preview failed", e);
            return CommonResult.error(500, "Preview failed: " + e.getMessage());
        }
    }
}