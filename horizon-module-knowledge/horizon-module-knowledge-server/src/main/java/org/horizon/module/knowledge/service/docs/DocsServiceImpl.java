package org.horizon.module.knowledge.service.docs;

import org.springframework.context.annotation.Lazy;
import org.horizon.module.knowledge.controller.admin.docs.vo.DocsPageReqVO;
import org.horizon.module.knowledge.controller.admin.docs.vo.DocsSaveReqVO;
import org.horizon.module.knowledge.controller.admin.docversions.vo.DocVersionsSaveReqVO;
import org.horizon.module.knowledge.dal.dataobject.docs.DocsDO;
import org.horizon.module.knowledge.dal.mapper.docs.DocsMapper;
import org.horizon.module.knowledge.service.docversions.DocVersionsService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import java.util.*;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.util.object.BeanUtils;
import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * RAG 知識庫文書 Service 实现类
 *
 * @author freedom
 */
@Service
@Validated
public class DocsServiceImpl implements DocsService {

    @Resource
    private DocsMapper docsMapper;

    @Resource
    @Lazy
    private DocVersionsService docVersionsService;

    @Override
    public Long createDocs(DocsSaveReqVO createReqVO) {

        // 插入
        DocsDO docs = BeanUtils.toBean(createReqVO, DocsDO.class);

//        docs.setNodeId(11L);
//        docs.setActiveVersionId(100L);
//        docs.setAttributionName("test");
//        docs.setDisplayFlag(1);
//        docs.setRemark("test");
          docs.setDeleted(false);

        String tenantId = String.valueOf(docs.getTenantId());

        docs.setCreator(tenantId);
        docs.setUpdater(tenantId);



        docsMapper.insert(docs);

        // 返回
        return docs.getId();
    }

    @Override
    public void updateDocs(DocsSaveReqVO updateReqVO) {
        // 校验存在
        validateDocsExists(updateReqVO.getId());
        // 更新
        DocsDO updateObj = BeanUtils.toBean(updateReqVO, DocsDO.class);
        docsMapper.updateById(updateObj);
    }

    @Override
    public void deleteDocs(Long id) {
        // 校验存在
        validateDocsExists(id);
        // 删除
        docsMapper.deleteById(id);
    }

    @Override
    public void deleteDocsListByIds(List<Long> ids) {
        // 删除
        docsMapper.deleteByIds(ids);
    }


    private void validateDocsExists(Long id) {
        if (docsMapper.selectById(id) == null) {
            throw exception(DOCS_NOT_EXISTS);
        }
    }

    @Override
    public DocsDO getDocs(Long id) {
        return docsMapper.selectById(id);
    }


    @Override
    public PageResult<DocsDO> getDocsPage(DocsPageReqVO pageReqVO) {
        List<DocsDO> list = docsMapper.selectPage(pageReqVO);
        long total = list.size();

        return new PageResult<>(list, total);
    }

}