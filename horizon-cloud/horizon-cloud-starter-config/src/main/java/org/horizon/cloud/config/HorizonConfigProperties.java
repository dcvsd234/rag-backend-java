package org.horizon.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "horizon.config")
public class HorizonConfigProperties {
    /** 是否启用（默认 true） */
    private boolean enabled = true;
    /** 是否启用刷新（默认 false；需要配合 spring cloud kubernetes reload） */
    private boolean refreshEnabled = false;
    /** k8s 命名空间；缺省=当前 Pod 所在 ns */
    private String namespace;
    /** 要加载的 ConfigMap 名称列表（按需） */
    private List<String> sources;
    /** 是否允许读取 Secret（默认 true） */
    private boolean secretsEnabled = true;

    // getters/setters
}