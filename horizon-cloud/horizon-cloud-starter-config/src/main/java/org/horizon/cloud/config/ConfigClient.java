package org.horizon.cloud.config;

public interface ConfigClient {
    String get(String key);
    <T> T bind(String prefix, Class<T> type);
}