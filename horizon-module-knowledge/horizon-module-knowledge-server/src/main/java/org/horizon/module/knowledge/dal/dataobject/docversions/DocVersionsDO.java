package org.horizon.module.knowledge.dal.dataobject.docversions;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 知識庫文書バージョン DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_doc_versions")
@KeySequence("knowledge_doc_versions_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocVersionsDO extends BaseDO {

    /**
     * 知识库文档版本ID
     */
    @TableId
    private Long id;
    /**
     * 知识库文档ID
     */
    private Long knowledgeDocsId;
    /**
     * 绑定实际存储的文件
     */
    private Long infraFileId;
    /**
     * 版本号
     */
    private String versionNo;
    /**
     * 绑定实际存储的文件
     */
    private String summary;
    /**
     * 備考
     */
    private String remark;


}