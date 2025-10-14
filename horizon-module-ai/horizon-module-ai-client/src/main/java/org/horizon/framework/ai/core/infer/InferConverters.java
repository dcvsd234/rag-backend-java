package org.horizon.framework.ai.core.infer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InferConverters {
    private static final Map<Class<?>, ToInferRequest<?>> REG = new ConcurrentHashMap<>();
    private InferConverters() {}

    public static <T> void register(Class<T> type, ToInferRequest<T> c) { REG.put(type, c); }

    @SuppressWarnings("unchecked")
    public static <T> ToInferRequest<T> get(Class<T> type) {
        ToInferRequest<?> c = REG.get(type);
        if (c == null) throw new IllegalStateException("No converter registered for " + type.getName());
        return (ToInferRequest<T>) c;
    }
}