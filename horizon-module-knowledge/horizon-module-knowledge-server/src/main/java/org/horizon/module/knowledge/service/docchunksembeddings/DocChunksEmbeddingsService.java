package org.horizon.module.knowledge.service.docchunksembeddings;

import java.util.*;
import javax.validation.*;

import org.apache.ibatis.annotations.Param;
import org.horizon.module.knowledge.controller.admin.docchunksembeddings.vo.*;
import org.horizon.module.knowledge.dal.dataobject.docchunksembeddings.DocChunksEmbeddingsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG 文書向量表（存储文本切片的向量表示） Service 接口
 *
 * @author 芋道源码
 */
public interface DocChunksEmbeddingsService {

    /**
     * 创建RAG 文書向量表（存储文本切片的向量表示）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDocChunksEmbeddings(@Valid DocChunksEmbeddingsSaveReqVO createReqVO);

    /**
     * 批量插入 RAG 文書向量表（存储文本切片的向量表示）
     *
     * @param list 待插入的数据
     * @return 插入的条数
     */
    int insertBatch(@Param("list") List<DocChunksEmbeddingsDO> list);

    /**
     * 更新RAG 文書向量表（存储文本切片的向量表示）
     *
     * @param updateReqVO 更新信息
     */
    void updateDocChunksEmbeddings(@Valid DocChunksEmbeddingsSaveReqVO updateReqVO);

    /**
     * 删除RAG 文書向量表（存储文本切片的向量表示）
     *
     * @param id 编号
     */
    void deleteDocChunksEmbeddings(Long id);

    /**
     * 批量删除RAG 文書向量表（存储文本切片的向量表示）
     *
     * @param ids 编号
     */
    void deleteDocChunksEmbeddingsListByIds(List<Long> ids);

    /**
     * 获得RAG 文書向量表（存储文本切片的向量表示）
     *
     * @param id 编号
     * @return RAG 文書向量表（存储文本切片的向量表示）
     */
    DocChunksEmbeddingsDO getDocChunksEmbeddings(Long id);

    /**
     * 获得RAG 文書向量表（存储文本切片的向量表示）分页
     *
     * @param pageReqVO 分页查询
     * @return RAG 文書向量表（存储文本切片的向量表示）分页
     */
    PageResult<DocChunksEmbeddingsDO> getDocChunksEmbeddingsPage(DocChunksEmbeddingsPageReqVO pageReqVO);

}
