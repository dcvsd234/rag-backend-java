package org.horizon.framework.ai.core.dto.generate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.horizon.framework.ai.core.dto.base.BaseReq;

import java.util.List;
import java.util.Map;

/** 文本生成/改写的请求 DTO */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateReq extends BaseReq {

    /** 必填：用户输入的问题/原文 */
    private String input;

    /** 必填：提示词/改写指令（风格、用途、限制） */
    private String instruction;

    /** 可选：受众，如 student / public / professional */
    private String audience;

    /** 可选：语气，如 formal / casual / rigorous / humorous */
    private String tone;

    /** 可选：输出语言，如 zh-CN / ja-JP / en */
    private String language;

    /** 可选：输出格式，PLAIN / MARKDOWN / BULLETS */
    private OutputFormat outputFormat;

    /** 可选：最大字数/字符数控制 */
    private Integer maxWords;

    /** 可选：附加上下文片段（轻量提示，非 RAG） */
    private List<String> context;

    /** 可选：后端路由使用的流水线名，如 policy / visa */
    private String pipelineName;

    /** 可选：模板与变量 */
    private String template;
    private Map<String, Object> templateVars;

    /** 可选：后处理需要移除的套话/短语 */
    private List<String> postRemovePhrases;

    /** 可选：采样参数（独立 DTO） */
    private GenParams genParams;

    /** 可选：输出模式 two_line | json */
    private String outputMode;

    /** 可选：是否返回原始输出（便于排障） */
    private Boolean returnRaw;

    public enum OutputFormat { PLAIN, MARKDOWN, BULLETS }
}