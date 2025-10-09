package org.horizon.module.qa.enums;

import org.horizon.framework.common.exception.ErrorCode;

/**
 * Mp 错误码枚举类
 *
 * mp 系统，使用 1_001_000_000 段
 */
public interface ErrorCodeConstants {

    ErrorCode USER_QUESTIONS_NOT_EXISTS = new ErrorCode(1_001_000_000, "RAG 用户问题表（终端用户的每一次提问记录）不存在");

    ErrorCode USER_ANSWERS_NOT_EXISTS = new ErrorCode(1_002_000_000, "RAG 用户回复表（保存终端用户每个提问对应的模型回复）不存在");

    ErrorCode USER_ANSWER_SOURCES_NOT_EXISTS = new ErrorCode(1_003_000_000, "RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）不存在");

    ErrorCode USER_SESSIONS_NOT_EXISTS = new ErrorCode(1_004_000_000, "RAG 会话表：一段连续对话的容器（多个问题与回答的集合）不存在");


}
