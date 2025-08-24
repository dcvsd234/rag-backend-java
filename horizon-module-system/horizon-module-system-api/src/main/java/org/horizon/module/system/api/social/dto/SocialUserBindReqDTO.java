package org.horizon.module.system.api.social.dto;

import org.horizon.framework.common.enums.UserTypeEnum;
import org.horizon.framework.common.validation.InEnum;
import org.horizon.module.system.enums.social.SocialTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "RPC 服务 - 绑定社交用户 Request DTO（通用 Provider）")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUserBindReqDTO {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @Schema(description = "用户类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(UserTypeEnum.class)
    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    @Schema(description = "第三方 Provider 类型（如 GitHub/Google/Twitter 等）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @InEnum(SocialTypeEnum.class) // 复用原有枚举；如需更细分可后续改为 ProviderTypeEnum
    @NotNull(message = "第三方 Provider 类型不能为空")
    private Integer providerType;

    @Schema(description = "授权码", requiredMode = Schema.RequiredMode.REQUIRED, example = "zsw")
    @NotEmpty(message = "授权码不能为空")
    private String code;

    @Schema(description = "state", requiredMode = Schema.RequiredMode.REQUIRED, example = "qtw")
    @NotEmpty(message = "state 不能为空")
    private String state;
}