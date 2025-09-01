package org.horizon.module.dataset.service.doc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horizon.module.dataset.api.vo.common.IdResp;
import org.horizon.module.dataset.api.vo.doc.UploadStatusResp;
import org.horizon.module.dataset.dal.dataobject.vector.DocChunkDO;
import org.horizon.module.dataset.api.enums.DocSourceType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Document ingestion service:
 * - accept upload / pasted text
 * - chunk text
 * - (TODO) embed chunks and save to vector store
 * - (TODO) persist raw doc / chunks metadata
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DocIngestService {

    // TODO: inject repositories / vector clients when ready
    // private final DocRepository docRepository;
    // private final DocChunkRepository docChunkRepository;
    // private final VectorStoreClient vectorStoreClient;

    /**
     * Handle file upload, then chunk & (later) embed.
     */
    public UploadStatusResp uploadDocument(MultipartFile file, DocSourceType sourceType) {
        if (file == null || file.isEmpty()) {
            return UploadStatusResp.builder()
                    .status("FAILED")
                    .errorMessage("File is empty")
                    .build();
        }
        try {
            final String docId = UUID.randomUUID().toString();
            final String filename = file.getOriginalFilename();
            final long fileSize = file.getSize();
            final String text = new String(file.getBytes(), StandardCharsets.UTF_8);

            log.info("📄 Upload received: docId={}, name={}, size={}, source={}",
                    docId, filename, fileSize, sourceType);

            // 1) chunk
            List<DocChunkDO> chunks = chunkText(text, 800, 200);

            // 2) (TODO) persist raw doc metadata
            // docRepository.save(...)

            // 3) (TODO) embed & save to vector DB
            embedAndSaveChunks(docId, chunks);

            // 4) (TODO) persist chunk rows
            // docChunkRepository.batchInsert(docId, chunks);

            return UploadStatusResp.builder()
                    .status("SUCCESS")
                    .docId(docId)
                    .fileName(filename)
                    .fileSize(fileSize)
                    .build();

        } catch (Exception e) {
            log.error("❌ uploadDocument failed", e);
            return UploadStatusResp.builder()
                    .status("FAILED")
                    .errorMessage("Upload failed: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Ingest plain text (for pasted content).
     */
    public UploadStatusResp ingestText(String text, DocSourceType sourceType) {
        if (text == null || text.trim().isEmpty()) {
            return UploadStatusResp.builder()
                    .status("FAILED")
                    .errorMessage("Text is empty")
                    .build();
        }
        try {
            final String docId = UUID.randomUUID().toString();
            log.info("📝 Ingest text: docId={}, length={}, source={}",
                    docId, text.length(), sourceType);

            List<DocChunkDO> chunks = chunkText(text, 800, 200);
            embedAndSaveChunks(docId, chunks);

            return UploadStatusResp.builder()
                    .status("SUCCESS")
                    .docId(docId)
                    // 非文件上传，这里无文件名/大小
                    .build();

        } catch (Exception e) {
            log.error("❌ ingestText failed", e);
            return UploadStatusResp.builder()
                    .status("FAILED")
                    .errorMessage("Ingest failed: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Very simple chunker:
     * - split by blank-line paragraphs
     * - greedy merge with maxChars and overlap window
     */
    private List<DocChunkDO> chunkText(String text, int maxChars, int overlapChars) {
        String[] paras = text.split("\\r?\\n\\s*\\r?\\n"); // split by blank line
        List<DocChunkDO> chunks = new ArrayList<DocChunkDO>();

        StringBuilder buf = new StringBuilder();
        int index = 1;
        for (String p : paras) {
            String para = (p == null) ? "" : p.trim(); // Java 8 兼容：用 trim 替代 strip
            if (para.isEmpty()) {
                continue;
            }

            // if adding this paragraph would exceed max, flush current chunk
            if (buf.length() > 0 && buf.length() + para.length() + 2 > maxChars) {
                chunks.add(buildChunk(index++, buf.toString()));
                // build overlap window
                String current = buf.toString();
                int start = Math.max(0, current.length() - overlapChars);
                buf = new StringBuilder(current.substring(start));
            }
            if (buf.length() > 0) {
                buf.append("\n\n");
            }
            buf.append(para);
        }
        if (buf.length() > 0) {
            chunks.add(buildChunk(index++, buf.toString()));
        }

        log.info("🔪 chunkText done: totalChunks={}", chunks.size());
        return chunks;
    }

    private DocChunkDO buildChunk(int index, String content) {
        DocChunkDO c = new DocChunkDO();
        c.setChunkIndex(index);
        c.setContent(content);
        c.setVector(null); // embedding will be filled later
        c.setDeleted(Boolean.FALSE);
        return c;
    }

    /**
     * (Placeholder) Embed and persist chunks into vector DB.
     * Replace with actual calls to your embedding service.
     */
    private void embedAndSaveChunks(String docId, List<DocChunkDO> chunks) {
        log.info("⚡ Embedding {} chunks for docId={}", chunks.size(), docId);
        // Example pseudo:
        // for (DocChunkDO c : chunks) {
        //     float[] vector = vectorStoreClient.embed(c.getContent());
        //     c.setVector(Arrays.toString(vector));
        //     vectorStoreClient.upsert(docId, c.getChunkIndex(), vector, c.getContent());
        // }
    }

    /**
     * Delete a document (raw + vectors + chunks).
     */
    public IdResp deleteDocument(Long docId) {
        log.info("🗑️ Deleting docId={}", docId);
        // TODO: remove raw doc, chunks, and vectors
        // docRepository.deleteById(docId);
        // docChunkRepository.deleteByDocId(docId);
        // vectorStoreClient.deleteNamespace(docId);
        return new IdResp(docId);
    }
}