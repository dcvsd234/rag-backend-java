package org.horizon.module.knowledge.enums;

import org.horizon.framework.common.exception.ErrorCode;

/**
 * Mp 错误码枚举类
 *
 * mp 系统，使用 1_010_000_000 段
 */
public interface ErrorCodeConstants {

    ErrorCode NODES_NOT_EXISTS = new ErrorCode(1_010_000_000, "RAG 知识库节点不存在");

    ErrorCode DOCS_NOT_EXISTS = new ErrorCode(1_020_000_000, "RAG 知識庫文書不存在");

    ErrorCode DOC_VERSIONS_NOT_EXISTS = new ErrorCode(1_030_000_000, "RAG 知識庫文書バージョン不存在");

    ErrorCode DOC_CHUNKS_EMBEDDINGS_NOT_EXISTS = new ErrorCode(1_040_000_000, "RAG 文書向量表（存储文本切片的向量表示）不存在");

    ErrorCode FAQS_NOT_EXISTS = new ErrorCode(1_050_000_000, "RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）不存在");

    ErrorCode FAQ_EMBEDDINGS_NOT_EXISTS = new ErrorCode(1_060_000_000, "FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）不存在");

    ErrorCode CATEGORIES_NOT_EXISTS = new ErrorCode(1_070_000_000, "RAG FAQ 分类不存在");

    ErrorCode CATEGORY_EMBEDDINGS_NOT_EXISTS = new ErrorCode(1_080_000_000, "RAG FAQ 分类向量不存在");

    ErrorCode FAQ_CATEGORY_LINKS_NOT_EXISTS = new ErrorCode(1_090_000_000, "FAQ分類关联不存在");

    ErrorCode DOC_CHUNKS_NOT_EXISTS = new ErrorCode(1_100_000_000, "RAG 文書切片表（文档分段后存储内容）不存在");
}
