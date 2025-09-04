package org.horizon.module.dataset.service.ingest;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.horizon.module.dataset.api.vo.doc.FileUploadResp;
import org.horizon.module.dataset.api.vo.doc.SubmitDocReq;
import org.horizon.module.dataset.api.vo.doc.UploadStatusResp;
import org.horizon.module.dataset.framework.file.StorageProps;
import org.horizon.module.infra.framework.file.core.client.FileClient;
import org.horizon.module.infra.framework.file.core.client.FileClientFactory;
import org.horizon.module.infra.framework.file.core.utils.FileTypeUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocIngestService {

    private final FileClientFactory fileClientFactory; // ✅ 用官方工厂
    private final StorageProps storageProps;           // 读 configId/basePath/domain

    public FileUploadResp upload(String tenantId, String leafId, MultipartFile file) throws Exception {
        FileClient client = fileClientFactory.getFileClient(storageProps.getConfigId());
        if (client == null) {
            throw new IllegalStateException(
                    "FileClient 未初始化，请检查 DatasetStorageInitializer 是否已正确创建存储客户端（configId="
                            + storageProps.getConfigId() + "）");
        }

        byte[] bytes = file.getBytes();
        String mime = file.getContentType();
        if (mime == null) {
            mime = FileTypeUtils.getMineType(bytes, file.getOriginalFilename());
        }

        String safeLeaf = StrUtil.isBlank(leafId) ? "_unassigned" : leafId.trim();
        String yyyymm = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String original = StrUtil.blankToDefault(file.getOriginalFilename(), "unnamed");
        String ext = original.contains(".") ? original.substring(original.lastIndexOf('.')) : "";

        // 统一 key：tenant/leaf/yyyyMM/uuid.ext
        String key = String.format("%s/%s/%s/%s%s", tenantId, safeLeaf, yyyymm, UUID.randomUUID(), ext);

        // ✅ 交给官方 client 保存；通常返回可访问 URL（取决于 client 实现）
        String url = client.upload(bytes, key, mime);

        // 这里没有DB，先用UUID占位 fileId
        String fileId = UUID.randomUUID().toString();

        return new FileUploadResp(
                fileId,
                url,         // 由 client 决定（local 通常是 domain + /uploads/...）
                key,         // 存储路径（相对）
                original,    // 原文件名
                file.getSize(),
                mime
        );
    }

    public UploadStatusResp submit(SubmitDocReq req) {
        // 省略参数校验与业务…
        return UploadStatusResp.builder()
                .status("SUCCESS")
                .docId(StrUtil.blankToDefault(req.getDocument().getId(), "doc-" + System.currentTimeMillis()))
                .fileName(req.getDocument().getFileName())
                .fileSize(0L)
                .build();
    }
}