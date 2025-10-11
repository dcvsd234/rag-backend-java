package org.horizon.module.trends.service;

import org.horizon.framework.common.util.object.BeanUtils;
import org.horizon.module.trends.controller.admin.vo.keyword.KeywordPageReqVO;
import org.horizon.module.trends.controller.admin.vo.keyword.KeywordSaveReqVO;
import org.horizon.module.trends.dal.dataobject.KeywordDO;
import org.horizon.module.trends.dal.mysql.KeywordMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import org.horizon.framework.security.core.util.SecurityFrameworkUtils;
import org.horizon.framework.common.pojo.PageResult;
import javax.annotation.Resource;

import java.util.List;

/**
 * trends keyword Service 实现类
 *
 */
@Service
@Validated
public class KeywordServiceImpl implements KeywordService {

    @Resource
    private KeywordMapper keywordMapper;

    @Override
    public PageResult<KeywordDO> selectPage(KeywordPageReqVO pageReqVO) {
        Long tenantId = SecurityFrameworkUtils.getLoginUser().getTenantId();
        // 计算分页参数
        int offset = (pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize();
        int limit = pageReqVO.getPageSize();

        // 查询数据
        List<KeywordDO> list = keywordMapper.selectPage(
                pageReqVO.getText(),
                tenantId,
                offset,
                limit
        );
        Long total = keywordMapper.selectPageCount(pageReqVO.getText(), tenantId);

        return new PageResult<>(list, total);
    }

}