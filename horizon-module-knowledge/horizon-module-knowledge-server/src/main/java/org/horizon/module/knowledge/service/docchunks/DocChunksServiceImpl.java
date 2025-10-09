package org.horizon.module.knowledge.service.docchunks;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.horizon.module.knowledge.controller.admin.docchunks.vo.*;
import org.horizon.module.knowledge.dal.dataobject.docchunks.DocChunksDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.knowledge.dal.mapper.docchunks.DocChunksMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * RAG 文書切片表（文档分段后存储内容） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DocChunksServiceImpl implements DocChunksService {

    @Resource
    private DocChunksMapper docChunksMapper;

    @Override
    public Long createDocChunks(DocChunksSaveReqVO createReqVO) {
        // 插入
        DocChunksDO docChunks = BeanUtils.toBean(createReqVO, DocChunksDO.class);
        docChunksMapper.insert(docChunks);

        // 返回
        return docChunks.getId();
    }

    @Override
    public void insertBatch(List<DocChunksDO> docChunksList) {
        if (docChunksList == null || docChunksList.isEmpty()) {
            return; // Prevent Null Pointer Exceptions
        }
        docChunksMapper.insertBatch(docChunksList);
    }


    @Override
    public void updateDocChunks(DocChunksSaveReqVO updateReqVO) {
        // 校验存在
        validateDocChunksExists(updateReqVO.getId());
        // 更新
        DocChunksDO updateObj = BeanUtils.toBean(updateReqVO, DocChunksDO.class);
        docChunksMapper.updateById(updateObj);
    }

    @Override
    public void deleteDocChunks(Long id) {
        // 校验存在
        validateDocChunksExists(id);
        // 删除
        docChunksMapper.deleteById(id);
    }

    @Override
    public void deleteDocChunksListByIds(List<Long> ids) {
        // 删除
        docChunksMapper.deleteByIds(ids);
    }


    private void validateDocChunksExists(Long id) {
        if (docChunksMapper.selectById(id) == null) {
            throw exception(DOC_CHUNKS_NOT_EXISTS);
        }
    }

    @Override
    public DocChunksDO getDocChunks(Long id) {
        return docChunksMapper.selectById(id);
    }

    @Override
    public PageResult<DocChunksDO> getDocChunksPage(DocChunksPageReqVO pageReqVO) {
        return docChunksMapper.selectPage(pageReqVO);
    }

}
