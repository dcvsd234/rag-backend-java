package org.horizon.framework.ai.core.infer.impl;
import org.horizon.framework.ai.core.dto.file.FileIngestorReq;
import org.horizon.framework.ai.core.dto.infer.InferRequest;
import org.horizon.framework.ai.core.infer.ToInferRequest;

import java.util.LinkedHashMap;
import java.util.Map;

public class FileIngestorToInfer implements ToInferRequest<FileIngestorReq> {

    @Override public String configKey() { return "file_ingestor"; }

    @Override public void validate(FileIngestorReq src) {
        if (src.getBucket() == null || src.getBucket().isEmpty()) throw new IllegalArgumentException("bucket required");
        if (src.getKey() == null || src.getKey().isEmpty())       throw new IllegalArgumentException("key required");
    }

    @Override public InferRequest convert(FileIngestorReq src) {
        validate(src);
        Map<String,Object> p = new LinkedHashMap<>();
        p.put("config_key", configKey());
        p.put("bucket", src.getBucket());
        return InferRequest.builder()
                .tenantId(src.getTenantId())
                .traceId(src.getTraceId())
                .langId(src.getLangId())
                .input(src.getKey())     // input → key
                .parameters(p)           // parameters.bucket
                .build();
    }
}