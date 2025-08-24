package org.horizon.module.infra.api.logger;

import org.horizon.framework.common.biz.infra.logger.ApiAccessLogCommonApi;
import org.horizon.framework.common.biz.infra.logger.dto.ApiAccessLogCreateReqDTO;
import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.module.infra.service.logger.ApiAccessLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.horizon.framework.common.pojo.CommonResult.success;

@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class ApiAccessLogApiImpl implements ApiAccessLogCommonApi {

    @Resource
    private ApiAccessLogService apiAccessLogService;

    @Override
    public CommonResult<Boolean> createApiAccessLog(ApiAccessLogCreateReqDTO createDTO) {
        apiAccessLogService.createApiAccessLog(createDTO);
        return success(true);
    }

}
