package org.horizon.module.dataset.dal.dataobject.qa;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * QA 文档数据对象
 * - 用于存储 QA 对应的原始文档信息
 * - 一个 QA 可以关联多个文档
 */
@TableName("qa_doc")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QaDocDO {

    /** 主键 ID */
    @TableId
    private Long id;

    /** 租户 ID */
    private String tenantId;

    /** 关联的 QA ID（指向 QaDO.id） */
    private Long qaId;

    /** 文档 ID（指向 DocDO.id 或外部存储 ID） */
    private Long docId;

    /** 文档名称（展示用） */
    private String docName;

    /** 文档来源类型（本地上传 / URL / 知识库等） */
    private String sourceType;

    /** 文档存储路径（本地路径或 OSS/MinIO URL） */
    private String storagePath;

    /** 文档状态（0=未处理, 1=已切片, 2=已向量化） */
    private Integer status;

    /** 文档大小（单位：字节） */
    private Long size;

    /** 文档格式（pdf, docx, txt, md ...） */
    private String format;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 是否删除（逻辑删除标记） */
    private Boolean deleted;
}