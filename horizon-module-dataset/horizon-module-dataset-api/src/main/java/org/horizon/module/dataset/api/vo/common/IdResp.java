package org.horizon.module.dataset.api.vo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Unified response object that only contains the generated ID.
 * Commonly used after create/insert operations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdResp {

    /** The generated primary key ID */
    private Long id;
}
