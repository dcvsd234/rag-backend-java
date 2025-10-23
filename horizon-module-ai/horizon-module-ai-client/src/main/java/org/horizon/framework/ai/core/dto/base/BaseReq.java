package org.horizon.framework.ai.core.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 请求基类
 * - 包含所有请求的公共元信息（租户、追踪、语言）
 */
@Data
@SuperBuilder        // ✅ 支持继承类的 builder
@NoArgsConstructor   // ✅ 保证子类也能使用无参构造
@AllArgsConstructor  // ✅ 保证子类也能用全参构造
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseReq {

    /** 租户 ID（多租户支持） */
    private String tenantId;

    /** 追踪 ID（用于跨服务调用链路追踪） */
    private String traceId;

    /** 语言 ID 或标识（可为 String 或 Integer，根据业务决定） */
    private Object langId;
}