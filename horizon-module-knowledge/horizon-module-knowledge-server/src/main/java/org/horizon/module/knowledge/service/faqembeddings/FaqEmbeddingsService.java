package org.horizon.module.knowledge.service.faqembeddings;

import java.util.*;
import javax.validation.*;
import org.horizon.module.knowledge.controller.admin.faqembeddings.vo.*;
import org.horizon.module.knowledge.dal.dataobject.faqembeddings.FaqEmbeddingsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * FAQ 向量テーブル（FAQ 文本のベクトル表現を保存） Service 接口
 *
 * @author 芋道源码
 */
public interface FaqEmbeddingsService {

    /**
     * 创建FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFaqEmbeddings(@Valid FaqEmbeddingsSaveReqVO createReqVO);

    /**
     * 更新FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）
     *
     * @param updateReqVO 更新信息
     */
    void updateFaqEmbeddings(@Valid FaqEmbeddingsSaveReqVO updateReqVO);

    /**
     * 删除FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）
     *
     * @param id 编号
     */
    void deleteFaqEmbeddings(Long id);

    /**
     * 批量删除FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）
     *
     * @param ids 编号
     */
    void deleteFaqEmbeddingsListByIds(List<Long> ids);

    /**
     * 获得FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）
     *
     * @param id 编号
     * @return FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）
     */
    FaqEmbeddingsDO getFaqEmbeddings(Long id);

    /**
     * 获得FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）分页
     *
     * @param pageReqVO 分页查询
     * @return FAQ 向量テーブル（FAQ 文本のベクトル表現を保存）分页
     */
    PageResult<FaqEmbeddingsDO> getFaqEmbeddingsPage(FaqEmbeddingsPageReqVO pageReqVO);

    /**
     * 批量插入 FAQ 向量
     *
     * @param list FAQ 向量 DO 列表
     */
    void insertBatch(@Valid List<FaqEmbeddingsDO> list);

}