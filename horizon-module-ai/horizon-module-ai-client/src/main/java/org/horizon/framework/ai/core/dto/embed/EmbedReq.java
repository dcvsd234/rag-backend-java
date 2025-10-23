package org.horizon.framework.ai.core.dto.embed;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.horizon.framework.ai.core.dto.base.BaseReq;

import java.util.List;
import java.util.Map;

/**
 * 向量化请求体 (embedding_service 用)
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbedReq extends BaseReq {

    /** 待转换的文本列表 */
    private List<String> texts;

    /** 可选，指定向量存储的命名空间 */
    private String namespace;

    /** 附加元信息（例如 tenant_id, doc_id 等） */
    @Builder.Default
    private Map<String, Object> metadata = new java.util.LinkedHashMap<>();
}