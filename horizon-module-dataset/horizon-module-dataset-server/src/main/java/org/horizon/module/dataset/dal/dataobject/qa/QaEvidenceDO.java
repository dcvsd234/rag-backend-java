package org.horizon.module.dataset.dal.dataobject.qa;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * QA 证据数据对象
 * - 用于存储 QA 关联的证据句
 * - 一个 QA 可以有多个证据句
 */
@TableName("qa_evidence")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QaEvidenceDO {

    /** 主键 ID */
    @TableId
    private Long id;

    /** 租户 ID */
    private String tenantId;

    /** 关联的 QA ID（指向 QaDO.id） */
    private Long qaId;

    /** 关联的文档 ID（指向 QaDocDO.id，可为空） */
    private Long qaDocId;

    /** 证据内容（原始句子/片段） */
    private String content;

    /** 来源信息（如页码、段落号、起止位置） */
    private String sourceMeta;

    /** 是否用户手动添加（true=手动，false=文档提取） */
    private Boolean manual;

    /** 证据排序（前端展示用，例如 1,2,3...） */
    private Integer sort;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 是否删除（逻辑删除标记） */
    private Boolean deleted;
}