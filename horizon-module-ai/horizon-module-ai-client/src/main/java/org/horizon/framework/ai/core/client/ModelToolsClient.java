package org.horizon.framework.ai.core.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horizon.framework.ai.core.dto.generate.GenerateResp;
import org.horizon.framework.ai.core.dto.infer.InferRequest;
import org.horizon.framework.ai.core.dto.infer.InferResponse;
import org.springframework.web.client.RestTemplate;

/**
 * ModelToolsClient
 * - 用于与模型服务（model_zoo / generator_service）交互
 * - 负责封装 /infer API 调用
 */
@Slf4j
@RequiredArgsConstructor
public class ModelToolsClient {

    /** HTTP 客户端 */
    private final RestTemplate restTemplate;

    /** 模型服务的基础 URL，例如 http://127.0.0.1:8020 */
    private final String baseUrl;

    /** 可选：鉴权 Token（如果服务端需要认证） */
    private final String apiKey;

    /**
     * 调用模型服务 /infer 接口
     *
     * @param req 推理请求体
     * @return 模型生成的结果
     */
    public <T extends InferResponse> T generate(InferRequest req, Class<T> respClass) {
        try {
            log.info("🚀 调用模型服务 | baseUrl={} | apiKey存在? {}", baseUrl, apiKey != null);

            T resp = restTemplate.postForObject(
                    baseUrl + "/infer",
                    req,
                    respClass
            );

            if (resp == null) {
                log.warn("⚠️ 模型服务返回为空");
            }
            return resp;
        } catch (Exception ex) {
            log.error("❌ 调用模型服务失败", ex);
            throw ex;
        }
    }
}