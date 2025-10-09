package org.horizon.module.knowledge.service.docchunks;

import java.util.*;
import javax.validation.*;
import org.horizon.module.knowledge.controller.admin.docchunks.vo.*;
import org.horizon.module.knowledge.dal.dataobject.docchunks.DocChunksDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG 文書切片表（文档分段后存储内容） Service 接口
 *
 * @author freedom
 */
public interface DocChunksService {

    /**
     * 创建RAG 文書切片表（文档分段后存储内容）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDocChunks(@Valid DocChunksSaveReqVO createReqVO);


    /**
     * Batch Insert into the RAG Document Chunk Table (Stores Content After Document Segmentation)
     *
     * @param docChunksList List of Chunk Data
     * @return Number of Inserted Records
     */
    void insertBatch(@Valid List<DocChunksDO> docChunksList);

    /**
     * 更新RAG 文書切片表（文档分段后存储内容）
     *
     * @param updateReqVO 更新信息
     */
    void updateDocChunks(@Valid DocChunksSaveReqVO updateReqVO);

    /**
     * 删除RAG 文書切片表（文档分段后存储内容）
     *
     * @param id 编号
     */
    void deleteDocChunks(Long id);

    /**
     * 批量删除RAG 文書切片表（文档分段后存储内容）
     *
     * @param ids 编号
     */
    void deleteDocChunksListByIds(List<Long> ids);

    /**
     * 获得RAG 文書切片表（文档分段后存储内容）
     *
     * @param id 编号
     * @return RAG 文書切片表（文档分段后存储内容）
     */
    DocChunksDO getDocChunks(Long id);

    /**
     * 获得RAG 文書切片表（文档分段后存储内容）分页
     *
     * @param pageReqVO 分页查询
     * @return RAG 文書切片表（文档分段后存储内容）分页
     */
    PageResult<DocChunksDO> getDocChunksPage(DocChunksPageReqVO pageReqVO);

}