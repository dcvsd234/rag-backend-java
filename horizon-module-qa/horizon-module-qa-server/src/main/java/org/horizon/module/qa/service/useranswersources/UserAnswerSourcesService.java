package org.horizon.module.qa.service.useranswersources;

import java.util.*;
import javax.validation.*;
import org.horizon.module.qa.controller.admin.useranswersources.vo.*;
import org.horizon.module.qa.dal.dataobject.useranswersources.UserAnswerSourcesDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;

/**
 * RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据） Service 接口
 *
 * @author 芋道源码
 */
public interface UserAnswerSourcesService {

    /**
     * 创建RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserAnswerSources(@Valid UserAnswerSourcesSaveReqVO createReqVO);

    /**
     * 更新RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）
     *
     * @param updateReqVO 更新信息
     */
    void updateUserAnswerSources(@Valid UserAnswerSourcesSaveReqVO updateReqVO);

    /**
     * 删除RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）
     *
     * @param id 编号
     */
    void deleteUserAnswerSources(Long id);

    /**
     * 批量删除RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）
     *
     * @param ids 编号
     */
    void deleteUserAnswerSourcesListByIds(List<Long> ids);

    /**
     * 获得RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）
     *
     * @param id 编号
     * @return RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）
     */
    UserAnswerSourcesDO getUserAnswerSources(Long id);

    /**
     * 获得RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）分页
     *
     * @param pageReqVO 分页查询
     * @return RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据）分页
     */
    PageResult<UserAnswerSourcesDO> getUserAnswerSourcesPage(UserAnswerSourcesPageReqVO pageReqVO);

}