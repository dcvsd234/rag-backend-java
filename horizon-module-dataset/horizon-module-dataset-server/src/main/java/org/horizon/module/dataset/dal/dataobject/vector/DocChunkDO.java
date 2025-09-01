package org.horizon.module.dataset.dal.dataobject.vector;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 文档切片数据对象
 * - 存储文档的分片内容（chunk）
 * - 每个文档可对应多个切片
 */
@TableName("doc_chunk")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocChunkDO {

    /** 主键 ID */
    @TableId
    private Long id;

    /** 租户 ID */
    private String tenantId;

    /** 文档 ID（关联原始文档表 DocDO.id） */
    private Long docId;

    /** 分片序号（保证顺序，例如 1,2,3...） */
    private Integer chunkIndex;

    /** 分片内容（原始文本） */
    private String content;

    /** 分片向量（存储为 JSON/向量表引用，取决于存储方式） */
    private String vector;

    /** 起始偏移位置 */
    private Integer startOffset;

    /** 结束偏移位置 */
    private Integer endOffset;

    /** 来源元信息（页码、段落号等） */
    private String sourceMeta;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 是否删除（逻辑删除标记） */
    private Boolean deleted;
}