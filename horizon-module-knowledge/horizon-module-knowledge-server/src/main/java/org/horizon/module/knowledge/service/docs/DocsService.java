package org.horizon.module.knowledge.service.docs;

import java.util.*;
import javax.validation.*;
import org.horizon.module.knowledge.controller.admin.docs.vo.*;
import org.horizon.module.knowledge.dal.dataobject.docs.DocsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG 知識庫文書 Service 接口
 *
 * @author 芋道源码
 */
public interface DocsService {

    /**
     * 创建RAG 知識庫文書
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDocs(@Valid DocsSaveReqVO createReqVO);

    /**
     * 更新RAG 知識庫文書
     *
     * @param updateReqVO 更新信息
     */
    void updateDocs(@Valid DocsSaveReqVO updateReqVO);

    /**
     * 删除RAG 知識庫文書
     *
     * @param id 编号
     */
    void deleteDocs(Long id);

    /**
     * 批量删除RAG 知識庫文書
     *
     * @param ids 编号
     */
    void deleteDocsListByIds(List<Long> ids);

    /**
     * 获得RAG 知識庫文書
     *
     * @param id 编号
     * @return RAG 知識庫文書
     */
    DocsDO getDocs(Long id);

    /**
     * 获得RAG 知識庫文書分页
     *
     * @param pageReqVO 分页查询
     * @return RAG 知識庫文書分页
     */
    PageResult<DocsDO> getDocsPage(DocsPageReqVO pageReqVO);

}