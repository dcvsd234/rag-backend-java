package org.horizon.module.knowledge.dal.dataobject.docchunks;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 文書切片表（文档分段后存储内容） DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_doc_chunks")
@KeySequence("knowledge_doc_chunks_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocChunksDO extends BaseDO {

    /**
     * 知识库文档切片ID
     */
    @TableId
    private Long id;
    /**
     * 知识库文档版本ID
     */
    private Long knowledgeDocVersionsId;
    /**
     * 文本内容
     */
    private String text;
    /**
     * 顺序号
     */
    private Integer chunkIndex;

    /**
     * 切片来源 ID
     */
    private String sourceChunkId;
     /**
     * 起始字符位置（原始文档中的偏移量）
     */
    private Integer startOffset;
    /**
     * 结束字符位置（原始文档中的偏移量）
     */
    private Integer endOffset;
    /**
     * 来源页码
     */
    private Integer pageNo;
    /**
     * 层级标题路径
     */
    private String headingPath;
    /**
     * 備考
     */
    private String remark;


}