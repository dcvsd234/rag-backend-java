package org.horizon.module.bpm.framework.rpc.config;

import org.horizon.module.bpm.api.event.CrmContractStatusListener;
import org.horizon.module.bpm.api.event.CrmReceivableStatusListener;
import org.horizon.module.system.api.dept.DeptApi;
import org.horizon.module.system.api.dept.PostApi;
import org.horizon.module.system.api.dict.DictDataApi;
import org.horizon.module.system.api.permission.PermissionApi;
import org.horizon.module.system.api.permission.RoleApi;
import org.horizon.module.system.api.sms.SmsSendApi;
import org.horizon.module.system.api.user.AdminUserApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "bpmRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {RoleApi.class, DeptApi.class, PostApi.class, AdminUserApi.class, SmsSendApi.class, DictDataApi.class,
        PermissionApi.class})
public class RpcConfiguration {

    // ========== 特殊：解决微 horizon-cloud 微服务场景下，跨服务（进程）无法 Listener 的问题 ==========

    @Bean
    @ConditionalOnMissingBean(name = "crmReceivableStatusListener")
    public CrmReceivableStatusListener crmReceivableStatusListener() {
        return new CrmReceivableStatusListener();
    }

    @Bean
    @ConditionalOnMissingBean(name = "crmContractStatusListener")
    public CrmContractStatusListener crmContractStatusListener() {
        return new CrmContractStatusListener();
    }

}
