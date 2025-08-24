package org.horizon.module.system.dal.dataobject.social;

import org.horizon.framework.mybatis.core.dataobject.BaseDO;
import org.horizon.module.system.enums.social.SocialTypeEnum;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 通用社交用户（OAuth2/OIDC）
 * - 已去除微信/中国特定字段
 * - 统一使用 externalId 作为社交平台唯一 ID
 *
 * @author Horizon
 */
@TableName(value = "system_social_user", autoResultMap = true)
@KeySequence("system_social_user_seq") // Oracle、PostgreSQL 等需要；MySQL 可不写
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialUserDO extends BaseDO {

    /** 自增主键 */
    @TableId
    private Long id;

    /** 社交平台的类型（枚举 {@link SocialTypeEnum}） */
    private Integer type;

    /** 平台唯一标识（如 GitHub userId / Google sub / Twitter handle） */
    private String externalId;

    /** 访问令牌 */
    private String token;

    /** 原始 Token 数据，一般是 JSON 格式 */
    private String rawTokenInfo;

    /** 用户昵称 */
    private String nickname;

    /** 用户头像 */
    private String avatar;

    /** 原始用户数据，一般是 JSON 格式 */
    private String rawUserInfo;

    /** 最近一次的授权码 */
    private String code;

    /** 最近一次的 state 值 */
    private String state;

}