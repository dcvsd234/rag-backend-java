package org.horizon.module.infra.api.logger;

import org.horizon.framework.common.biz.infra.logger.ApiErrorLogCommonApi;
import org.horizon.framework.common.biz.infra.logger.dto.ApiErrorLogCreateReqDTO;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.infra.service.logger.ApiErrorLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.horizon.framework.common.pojo.CommonResult.success;

@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class ApiErrorLogApiImpl implements ApiErrorLogCommonApi {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @Override
    public CommonResult<Boolean> createApiErrorLog(ApiErrorLogCreateReqDTO createDTO) {
        apiErrorLogService.createApiErrorLog(createDTO);
        return success(true);
    }

}
