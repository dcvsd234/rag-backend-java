package org.horizon.framework.ai.core.dto.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.horizon.framework.ai.core.dto.infer.InferResponse;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor                 // ✅ 必须：给 Jackson 一个无参构造
@AllArgsConstructor                // 可选
@Jacksonized                       // ✅ 允许用 Builder 路径反序列化（更稳）
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileIngestorResp extends InferResponse {

    /** 文本切片集合 */
    @Builder.Default
    private List<Chunk> chunks = new ArrayList<>();
}