package org.horizon.module.knowledge.service.docversions;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.knowledge.controller.admin.docversions.vo.*;
import org.horizon.module.knowledge.dal.dataobject.docversions.DocVersionsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.knowledge.dal.mapper.docversions.DocVersionsMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * RAG 知識庫文書バージョン Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DocVersionsServiceImpl implements DocVersionsService {

    @Resource
    private DocVersionsMapper docVersionsMapper;

    @Override
    public Long createDocVersions(DocVersionsSaveReqVO createReqVO) {
        // 插入
        DocVersionsDO docVersions = BeanUtils.toBean(createReqVO, DocVersionsDO.class);
        docVersionsMapper.insert(docVersions);

        // 返回
        return docVersions.getId();
    }


    @Override
    public int updateKnowledgeDocsIdById(DocVersionsSaveReqVO createReqVO) {

        DocVersionsDO docVersions = BeanUtils.toBean(createReqVO, DocVersionsDO.class);
        int index=docVersionsMapper.updateKnowledgeDocsIdById(docVersions);

        return index;
    }




    @Override
    public void updateDocVersions(DocVersionsSaveReqVO updateReqVO) {
        // 校验存在
        validateDocVersionsExists(updateReqVO.getId());
        // 更新
        DocVersionsDO updateObj = BeanUtils.toBean(updateReqVO, DocVersionsDO.class);
        docVersionsMapper.updateById(updateObj);
    }

    @Override
    public void deleteDocVersions(Long id) {
        // 校验存在
        validateDocVersionsExists(id);
        // 删除
        docVersionsMapper.deleteById(id);
    }

    @Override
    public void deleteDocVersionsListByIds(List<Long> ids) {
        // 删除
        docVersionsMapper.deleteByIds(ids);
    }


    private void validateDocVersionsExists(Long id) {
        if (docVersionsMapper.selectById(id) == null) {
            throw exception(DOC_VERSIONS_NOT_EXISTS);
        }
    }

    @Override
    public DocVersionsDO getDocVersions(Long id) {
        return docVersionsMapper.selectById(id);
    }

    @Override
    public PageResult<DocVersionsDO> getDocVersionsPage(DocVersionsPageReqVO pageReqVO) {
        return docVersionsMapper.selectPage(pageReqVO);
    }

}