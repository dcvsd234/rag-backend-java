package org.horizon.module.trends.controller.admin.vo.keyword;

import org.horizon.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 -关键词分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class KeywordPageReqVO extends PageParam {

    @Schema(description = "关键词名称，模糊匹配")
    private String text;
    private Number page;
    private Number pageSize;

}
