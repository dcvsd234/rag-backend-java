package org.horizon.module.knowledge.service.faqs;

import java.util.*;
import javax.validation.*;
import org.horizon.module.knowledge.controller.admin.faqs.vo.*;
import org.horizon.module.knowledge.dal.dataobject.faqs.FaqsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点） Service 接口
 *
 * @author 芋道源码
 */
public interface FaqsService {

    /**
     * 创建RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFaqs(@Valid FaqsSaveReqVO createReqVO);

    /**
     * 批量插入 RAG FAQ 主表（统一管理FAQ，支持关联文档版本和知识库节点）
     *
     * @param faqDOList FAQ 列表
     * @return 插入条数
     */
    int insertBatch(@Valid List<FaqsDO> faqDOList);

    /**
     * 更新RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）
     *
     * @param updateReqVO 更新信息
     */
    void updateFaqs(@Valid FaqsSaveReqVO updateReqVO);

    /**
     * 删除RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）
     *
     * @param id 编号
     */
    void deleteFaqs(Long id);

    /**
     * 批量删除RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）
     *
     * @param ids 编号
     */
    void deleteFaqsListByIds(List<Long> ids);

    /**
     * 获得RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）
     *
     * @param id 编号
     * @return RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）
     */
    FaqsDO getFaqs(Long id);

    /**
     * 获得RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）分页
     *
     * @param pageReqVO 分页查询
     * @return RAG FAQ主表（统一管理FAQ，支持关联文档版本和知识库节点）分页
     */
    PageResult<FaqsDO> getFaqsPage(FaqsPageReqVO pageReqVO);

}