package org.horizon.module.knowledge.service.docchunksembeddings;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.knowledge.controller.admin.docchunksembeddings.vo.*;
import org.horizon.module.knowledge.dal.dataobject.docchunksembeddings.DocChunksEmbeddingsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.knowledge.dal.mapper.docchunksembeddings.DocChunksEmbeddingsMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * RAG 文書向量表（存储文本切片的向量表示） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DocChunksEmbeddingsServiceImpl implements DocChunksEmbeddingsService {

    @Resource
    private DocChunksEmbeddingsMapper docChunksEmbeddingsMapper;

    @Override
    public Long createDocChunksEmbeddings(DocChunksEmbeddingsSaveReqVO createReqVO) {
        // 插入
        DocChunksEmbeddingsDO docChunksEmbeddings = BeanUtils.toBean(createReqVO, DocChunksEmbeddingsDO.class);
        docChunksEmbeddingsMapper.insert(docChunksEmbeddings);

        // 返回
        return docChunksEmbeddings.getId();
    }

    @Override
    public int insertBatch(List<DocChunksEmbeddingsDO> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return docChunksEmbeddingsMapper.insertBatch(list);
    }
    @Override
    public void updateDocChunksEmbeddings(DocChunksEmbeddingsSaveReqVO updateReqVO) {
        // 校验存在
        validateDocChunksEmbeddingsExists(updateReqVO.getId());
        // 更新
        DocChunksEmbeddingsDO updateObj = BeanUtils.toBean(updateReqVO, DocChunksEmbeddingsDO.class);
        docChunksEmbeddingsMapper.updateById(updateObj);
    }



    @Override
    public void deleteDocChunksEmbeddings(Long id) {
        // 校验存在
        validateDocChunksEmbeddingsExists(id);
        // 删除
        docChunksEmbeddingsMapper.deleteById(id);
    }

    @Override
    public void deleteDocChunksEmbeddingsListByIds(List<Long> ids) {
        // 删除
        docChunksEmbeddingsMapper.deleteByIds(ids);
    }


    private void validateDocChunksEmbeddingsExists(Long id) {
        if (docChunksEmbeddingsMapper.selectById(id) == null) {
            throw exception(DOC_CHUNKS_EMBEDDINGS_NOT_EXISTS);
        }
    }

    @Override
    public DocChunksEmbeddingsDO getDocChunksEmbeddings(Long id) {
        return docChunksEmbeddingsMapper.selectById(id);
    }

    @Override
    public PageResult<DocChunksEmbeddingsDO> getDocChunksEmbeddingsPage(DocChunksEmbeddingsPageReqVO pageReqVO) {
        return docChunksEmbeddingsMapper.selectPage(pageReqVO);
    }

}