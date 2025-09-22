package org.horizon.module.knowledge.enums;

import org.horizon.framework.common.exception.ErrorCode;

/**
 * Mp 错误码枚举类
 *
 * mp 系统，使用 1_010_000_000 段
 */
public interface ErrorCodeConstants {

    ErrorCode NODES_NOT_EXISTS = new ErrorCode(1_010_000_000, "RAG 知识库节点不存在");

}
