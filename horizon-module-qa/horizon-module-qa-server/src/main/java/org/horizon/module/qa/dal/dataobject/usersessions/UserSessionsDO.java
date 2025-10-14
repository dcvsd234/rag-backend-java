package org.horizon.module.qa.dal.dataobject.usersessions;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

/**
 * RAG 会话表：一段连续对话的容器（多个问题与回答的集合） DO
 *
 * @author 芋道源码
 */
@TableName("qa_user_sessions")
@KeySequence("qa_user_sessions_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionsDO extends BaseDO {

    /**
     * 会话ID
     */
    @TableId
    private Long id;
    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 匿名用户ID
     */
    private Long anonUserId;

    /**
     * 注册用户ID（匿名时为空）
     */
    private Long userId;
    /**
     * 会话标识（前端持有，标识同一段对话）
     */
    private String sessionToken;
    /**
     * 累计上下文 token（用于长度上限控制，可按需维护）
     */
    private Integer tokenUsed;
    /**
     * 最近一次交互时间（每次问/答更新）
     */
    private LocalDateTime lastActivity;
    /**
     * 会话开始时间
     */
    private LocalDateTime startTime;
    /**
     * 会话结束时间（关闭/过期写入）
     */
    private LocalDateTime endTime;
    /**
     * 会话状态：active/closed/expired
     */
    private String status;


}