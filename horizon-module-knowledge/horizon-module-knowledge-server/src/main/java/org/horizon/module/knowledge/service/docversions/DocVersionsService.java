package org.horizon.module.knowledge.service.docversions;

import java.util.*;
import javax.validation.*;
import org.horizon.module.knowledge.controller.admin.docversions.vo.*;
import org.horizon.module.knowledge.dal.dataobject.docversions.DocVersionsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG 知識庫文書バージョン Service 接口
 *
 * @author 芋道源码
 */
public interface DocVersionsService {

    /**
     * 创建RAG 知識庫文書バージョン
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDocVersions(@Valid DocVersionsSaveReqVO createReqVO);

    /**
     * 更新RAG 知識庫文書バージョン
     *
     * @param updateReqVO 更新信息
     */
    void updateDocVersions(@Valid DocVersionsSaveReqVO updateReqVO);


    /**
     * 更新RAG knowledge_docs_id
     *
     * @param updateReqVO 更新信息
     */
    int updateKnowledgeDocsIdById(DocVersionsSaveReqVO updateReqVO);

    /**
     * 删除RAG 知識庫文書バージョン
     *
     * @param id 编号
     */
    void deleteDocVersions(Long id);

    /**
     * 批量删除RAG 知識庫文書バージョン
     *
     * @param ids 编号
     */
    void deleteDocVersionsListByIds(List<Long> ids);

    /**
     * 获得RAG 知識庫文書バージョン
     *
     * @param id 编号
     * @return RAG 知識庫文書バージョン
     */
    DocVersionsDO getDocVersions(Long id);

    /**
     * 获得RAG 知識庫文書バージョン分页
     *
     * @param pageReqVO 分页查询
     * @return RAG 知識庫文書バージョン分页
     */
    PageResult<DocVersionsDO> getDocVersionsPage(DocVersionsPageReqVO pageReqVO);

}
