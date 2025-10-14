package org.horizon.framework.ai.core.dto.embed;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.horizon.framework.ai.core.dto.infer.InferResponse;

import java.util.List;
import java.util.Map;

/**
 * 文本向量化 + 存储响应体
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbedResp extends InferResponse {

    /** 嵌入向量列表，对应输入的每条文本 */
    private List<List<Float>> embeddings;

    /** 存储后端返回的向量 ID 列表（若有写库操作） */
    private List<String> ids;

    /** 写入的命名空间（tenant/collection） */
    private String namespace;

    /** 是否已写入存储后端 */
    private Boolean stored;

    /** 顶层调用元信息（例如 service、count、dim、model_name、normalize 等） */
    private Map<String, Object> metadata = new java.util.LinkedHashMap<>();

    /** 每条向量对应的元信息列表（index/len/dim 等） */
    private List<Map<String, Object>> perSample;
}