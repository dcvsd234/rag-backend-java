package org.horizon.framework.ai.core.dto.generate;

public class Usage {
    /** 输入 token 数 */
    private Integer promptTokens;
    /** 输出 token 数 */
    private Integer completionTokens;
    /** 总 token 数 */
    private Integer totalTokens;
}
