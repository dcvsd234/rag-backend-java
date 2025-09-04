package org.horizon.module.dataset.framework.file;


import lombok.RequiredArgsConstructor;
import org.horizon.module.infra.framework.file.core.client.FileClientFactory;
import org.horizon.module.infra.framework.file.core.client.local.LocalFileClientConfig;
import org.horizon.module.infra.framework.file.core.enums.FileStorageEnum;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(StorageProps.class) // 你自定义的 storage 配置类
@ConditionalOnClass(FileClientFactory.class)
public class DatasetStorageInitializer {

    private final FileClientFactory fileClientFactory;
    private final StorageProps storageProps;

    @Bean
    public ApplicationRunner datasetFileClientInitializer() {
        return args -> {
            LocalFileClientConfig cfg = new LocalFileClientConfig();
            cfg.setBasePath(storageProps.getBasePath()); // e.g. ./uploads
            cfg.setDomain(storageProps.getDomain());     // e.g. http://localhost:48080
            fileClientFactory.createOrUpdateFileClient(
                    storageProps.getConfigId(),          // e.g. 1
                    FileStorageEnum.LOCAL.getStorage(),  // 指定 LOCAL
                    cfg
            );
        };
    }
}