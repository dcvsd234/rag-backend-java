package org.horizon.module.knowledge.service.document;

import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.knowledge.api.doc.dto.SubmitDocReqDTO;

public interface DocumentDatasetService {

    /**
     * 提交文档及 FAQ 数据
     *
     * @param req 提交请求 DTO（包含文档元数据 + FAQ 列表）
     * @return 提交状态 DTO
     */
    CommonResult submit(SubmitDocReqDTO req);
}