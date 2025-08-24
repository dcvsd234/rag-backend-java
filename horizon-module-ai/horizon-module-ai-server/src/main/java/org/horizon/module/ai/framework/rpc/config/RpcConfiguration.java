package org.horizon.module.ai.framework.rpc.config;

import org.horizon.module.infra.api.file.FileApi;
import org.horizon.module.system.api.user.AdminUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "aiRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {FileApi.class, AdminUserApi.class})
public class RpcConfiguration {
}
