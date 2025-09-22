package org.horizon.module.knowledge.controller.admin.qa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horizon.framework.ai.core.client.ModelToolsClient;
import org.horizon.framework.ai.core.dto.generate.GenerateResp;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.knowledge.controller.admin.qa.vo.QaPreviewReqVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.horizon.framework.common.pojo.CommonResult.success;

/**
 * User App - QA Preview
 * 接收租户提交的「问题 / 提示词 / 证据等」组合（InferRequest），
 * 透传到 model_zoo /infer，返回模型预览结果（不落库）。
 */
@Tag(name = "租户 - 提示词验证")
@RestController
@RequestMapping("/app/dataset/qa")
@Validated
@Slf4j
@RequiredArgsConstructor // 自动注入构造器
public class QaPreviewController {

    private final ModelToolsClient modelToolsClient;

    @PostMapping("/preview")
    @Operation(summary = "Preview QA answer with evidences")
    public CommonResult<GenerateResp> preview(@RequestBody @Valid QaPreviewReqVO vo,
                                              @RequestHeader(value = "tenant-id", required = false) String tenantIdHdr) {

        // —— 统一补齐后端侧的默认参数 —— //
        final String traceId   = "req-" + UUID.randomUUID(); // 可换成你的 Trace 工具
        final String tenantId  = (tenantIdHdr != null && !tenantIdHdr.isEmpty()) ? tenantIdHdr : "tenant-a001";
        final String audience  = "学生";
        final String pipeline  = "policy";
        final String configKey = "generator_service";
        final Integer langId   = 81;

        // —— 封装成 InferRequest（就是你要的 /infer JSON 结构）—— //
//        InferRequest req = InferRequest.builder()
//                .input(vo.getQuestion())               // 顶层 input
//                .parameters(InferRequest.Parameters.builder()
//                        .config_key(configKey)
//                        .pipeline_name(pipeline)
//                        .audience(audience)
//                        .trace_id(traceId)
//                        .tenant_id(tenantId)
//                        .lang_id(langId)
//                        .template(vo.getTemplate())
//                        .template_vars(Map.of(
//                                // 关键：把前端 question / evidences 填到模板变量里
//                                "policy_text", vo.getQuestion(),
//                                "evidence", vo.getEvidences()
//                        ))
//                        .post_remove_phrases(List.of("# 政策説明:", "# 要約:", "まとめ：", "結論："))
//                        .gen_params(GenParams.builder()
//                                .maxTokens(80)
//                                .temperature(0.0)
//                                .topP(0.2)
//                                .topK(20)
//                                .repeatPenalty(1.15)
//                                .stop(List.of("</s>", "\n質問:", "\n証拠:", "\n# ", "回答例:", "\n例:"))
//                                .build())
//                        .build())
//                .build();


        // 打印验证日志


        // 调用模型服务
//        GenerateResp resp = modelToolsClient.generate(req);

        return success(null);
    }
}