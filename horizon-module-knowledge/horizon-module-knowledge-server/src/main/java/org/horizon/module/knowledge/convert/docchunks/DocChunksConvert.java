package org.horizon.module.knowledge.convert.docchunks;

import org.horizon.framework.ai.core.dto.file.Chunk;
import org.horizon.framework.ai.core.dto.file.FileIngestorResp;
import org.horizon.module.knowledge.dal.dataobject.docchunks.DocChunksDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Mapper
public interface DocChunksConvert {

    DocChunksConvert INSTANCE = Mappers.getMapper(DocChunksConvert.class);

    // 单个 Chunk -> DocChunksDO（部分字段在 @AfterMapping 补充）
    @Mappings({
            @Mapping(source = "chunkId",    target = "sourceChunkId"),
            @Mapping(source = "text",       target = "text"),
            @Mapping(source = "startIndex", target = "startOffset"),
            @Mapping(source = "endIndex",   target = "endOffset"),
            // 以下字段留给 @AfterMapping 设置：
            @Mapping(target = "knowledgeDocVersionsId", ignore = true),
            @Mapping(target = "chunkIndex",             ignore = true),
            @Mapping(target = "pageNo",                 ignore = true),
            @Mapping(target = "headingPath",            ignore = true),
            @Mapping(target = "remark",                 ignore = true)
    })
    DocChunksDO toDO(Chunk src);

    // List 映射
    List<DocChunksDO> toDOList(List<Chunk> src);

    // 入口：从 FileIngestorResp 直接转 List<DocChunksDO>
    default List<DocChunksDO> fromResp(FileIngestorResp resp, Long docVersionId) {
        if (resp == null || resp.getChunks() == null || resp.getChunks().isEmpty()) {
            return new ArrayList<>();
        }
        AtomicInteger idx = new AtomicInteger(0);
        List<DocChunksDO> list = toDOList(resp.getChunks());
        for (int i = 0; i < list.size(); i++) {
            afterFill(list.get(i), resp.getChunks().get(i), docVersionId, idx);
        }
        return list;
    }

    // 钩子：补充版本ID、序号、metadata 等
    @AfterMapping
    default void afterFill(@MappingTarget DocChunksDO target,
                           Chunk src,
                           @Context Long docVersionId,
                           @Context AtomicInteger indexHolder) {
        target.setKnowledgeDocVersionsId(docVersionId);
        target.setChunkIndex(indexHolder.getAndIncrement());

        Object page = src.getMetadata() != null ? src.getMetadata().get("page") : null;
        if (page instanceof Number) {
            target.setPageNo(((Number) page).intValue());
        }
        Object heading = src.getMetadata() != null ? src.getMetadata().get("heading_path") : null;
        if (heading != null) {
            target.setHeadingPath(heading.toString());
        }

        // remark 继续保留调试信息（可选）
        if (src.getChunkId() != null) {
            target.setRemark("source_chunk_id=" + src.getChunkId());
        }
    }
}