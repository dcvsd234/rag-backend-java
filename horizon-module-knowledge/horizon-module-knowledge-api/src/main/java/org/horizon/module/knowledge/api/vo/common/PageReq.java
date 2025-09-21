package org.horizon.module.knowledge.api.vo.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Unified pagination request object.  这个地方框架应该有，暂时这样使用
 * Used in query APIs that require paging.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageReq {

    /** Current page number (starting from 1) */
    private Integer pageNo = 1;

    /** Number of records per page */
    private Integer pageSize = 10;
}