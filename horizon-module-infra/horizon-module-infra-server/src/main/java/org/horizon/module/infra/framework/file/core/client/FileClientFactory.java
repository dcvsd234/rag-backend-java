package org.horizon.module.infra.framework.file.core.client;

import org.horizon.module.infra.framework.file.core.enums.FileStorageEnum;

/**
 * 纯文件客户端工厂（infra 层）
 *
 * <p>只负责创建与获取 {@link FileClient}。不包含任何“上传并返回业务 VO”的逻辑，
 * 以避免 infra → dataset 的上行依赖与循环依赖。</p>
 */
public interface FileClientFactory {

    /**
     * 根据配置编号获取文件客户端。
     *
     * @param configId 配置编号
     * @return 文件客户端，若未初始化返回 {@code null}
     */
    FileClient getFileClient(Long configId);

    /**
     * 创建或更新文件客户端。
     *
     * @param configId 配置编号
     * @param storage  存储器枚举 {@link FileStorageEnum}
     * @param config   存储配置
     * @param <Config> 配置类型
     */
    <Config extends FileClientConfig> void createOrUpdateFileClient(Long configId, Integer storage, Config config);

}