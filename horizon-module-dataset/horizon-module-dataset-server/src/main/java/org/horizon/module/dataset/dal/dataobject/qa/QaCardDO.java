package org.horizon.module.dataset.dal.dataobject.qa;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
        import java.time.LocalDateTime;

/**
 * QA 卡片数据对象
 * - 用于存储 QA 的卡片化信息，便于前端快速展示
 */
@TableName("qa_card")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QaCardDO {

    /** 主键 ID */
    @TableId
    private Long id;

    /** 租户 ID */
    private String tenantId;

    /** 关联的 QA ID（指向 QaDO.id） */
    private Long qaId;

    /** 卡片标题（通常取问题或简短摘要） */
    private String title;

    /** 卡片描述（提示词、答案摘要等） */
    private String description;

    /** 标签（JSON 数组字符串，例如 ["能源政策","学生"]） */
    private String tagsJson;

    /** 封面图 URL，可选 */
    private String coverUrl;

    /** 状态（0=未发布, 1=已发布, 2=下线） */
    private Integer status;

    /** 排序优先级 */
    private Integer sort;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 是否删除（逻辑删除标记） */
    private Boolean deleted;

    // ========== 非表字段（MyBatis Plus 忽略） ==========

    /** 展示用标签数组（由 tagsJson 反序列化） */
    @TableField(exist = false)
    private java.util.List<String> tags;
}