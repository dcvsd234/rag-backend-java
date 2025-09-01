package org.horizon.framework.ai.core.dto.generate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** Sampling parameters (independent DTO, reusable across requests) */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenParams {

    /** Maximum number of tokens to generate */
    private Integer maxTokens;

    /** Sampling temperature (higher = more random, lower = more deterministic) */
    private Double temperature;

    /** Nucleus sampling threshold (keep tokens until cumulative probability reaches topP) */
    private Double topP;

    /** Top-K sampling (limit sampling pool to K candidates) */
    private Integer topK;

    /** Repetition penalty (>1 discourages repeated tokens/phrases) */
    private Double repeatPenalty;

    /** List of stop words/phrases; generation stops when encountered */
    private List<String> stop;

    /** Random seed (fixed value ensures reproducibility) */
    private Integer seed;
}