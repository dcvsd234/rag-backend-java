package org.horizon.module.dataset.service.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horizon.module.dataset.framework.file.StorageProps;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final StorageProps storageProps;

    /**
     * 解析 basePath（相对路径 -> 绝对路径）
     */
    public String resolveBasePath() {
        String path = storageProps.getBasePath();
        if (!path.startsWith("/") && !path.contains(":")) {
            return Paths.get(System.getProperty("user.dir"), path).toAbsolutePath().toString();
        }
        return path;
    }

    /**
     * 保存文件
     *
     * @param key      相对路径 key，例如 "t-001/leaf-unassigned/202509/xxx.xlsx"
     * @param content  文件内容
     * @return 文件完整路径
     */
    public String saveFile(String key, byte[] content) throws IOException {
        Path root = Paths.get(resolveBasePath());
        Path fullPath = root.resolve(key).normalize();

        // 确保目录存在
        Files.createDirectories(fullPath.getParent());

        // 写入文件
        Files.write(fullPath, content);

        log.info("保存文件到本地: {}", fullPath);
        return fullPath.toString();
    }

    /**
     * 获取文件 URL
     *
     * @param key 相对路径 key
     * @return 可访问的 URL
     */
    public String getFileUrl(String key) {
        String url = String.format("%s/%s", storageProps.getDomain(), key);
        log.debug("生成文件 URL: {}", url);
        return url;
    }
}