package org.horizon.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${horizon.info.base-package}
@SpringBootApplication(scanBasePackages = {"${horizon.info.base-package}.server", "${horizon.info.base-package}.module", "org.horizon.framework.ai", "org.horizon.module.knowledge", "org.horizon.module.hotpoint"},
        excludeName = {
            // RPC 相关
//            "org.springframework.cloud.openfeign.FeignAutoConfiguration",
//            "org.horizon.module.system.framework.rpc.config.RpcConfiguration"
        })
public class HorizonServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HorizonServerApplication.class, args);
//        new SpringApplicationBuilder(HorizonServerApplication.class)
//                .applicationStartup(new BufferingApplicationStartup(20480))
//                .run(args);
    }

}
