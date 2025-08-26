package org.horizon.cloud.config;

import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

class DefaultConfigClient implements ConfigClient {
    private final Environment env;

    DefaultConfigClient(Environment env) {
        this.env = env;
    }
    @Override public String get(String key) {
        return env.getProperty(key);
    }
    @Override public <T> T bind(String prefix, Class<T> type) {
        return Binder.get(env).bind(prefix, Bindable.of(type)).orElse(null);
    }
}