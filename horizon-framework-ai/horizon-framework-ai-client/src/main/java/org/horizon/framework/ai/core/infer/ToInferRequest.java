package org.horizon.framework.ai.core.infer;

import org.horizon.framework.ai.core.dto.infer.InferRequest;

public interface ToInferRequest<T> {

    /** 返回配置中心中的 config_key（如 "file_ingestor"、"prompt_gen"、"generator_service"） */
    String configKey();

    /** 主转换：把任意业务请求 T 转为 InferRequest */
    InferRequest convert(T src);

    /** 简单校验钩子（可选实现） */
    default void validate(T src) {}
}