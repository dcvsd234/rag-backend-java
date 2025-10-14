package org.horizon.module.qa.dal.dataobject.useranswersources;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 回复来源表（记录答案所用的FAQ/文档/切片/AI等证据） DO
 *
 * @author 芋道源码
 */
@TableName("qa_user_answer_sources")
@KeySequence("qa_user_answer_sources_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerSourcesDO extends BaseDO {

    /**
     * 来源记录ID
     */
    @TableId
    private Long id;
    /**
     * 对应的问题ID（qa_user_questions.id）
     */
    private Long questionsId;
    /**
     * 来源类型（faq/doc/chunk/ai）
     */
    private String sourceType;
    /**
     * 具体来源ID（faq_id/doc_id/chunk_id）
     */
    private Long sourceId;
    /**
     * 片段内起始偏移
     */
    private Integer spanStart;
    /**
     * 片段内结束偏移
     */
    private Integer spanEnd;
    /**
     * 相似度得分
     */
    private Double score;
    /**
     * 排名顺序
     */
    private Integer rank;


}