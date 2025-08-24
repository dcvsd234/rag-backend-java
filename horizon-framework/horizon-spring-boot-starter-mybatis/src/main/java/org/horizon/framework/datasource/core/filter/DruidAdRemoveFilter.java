package org.horizon.framework.datasource.core.filter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Druid 监控页面 common.js 去广告过滤器
 *
 * 说明：
 * 1) 不再依赖 com.alibaba.druid.util.Utils，避免编译期绑定 Druid；
 * 2) 若未引入 Druid 或资源不存在，则什么都不做，保证运行安全；
 * 3) 仅在资源存在时复写响应内容。
 */
public class DruidAdRemoveFilter extends OncePerRequestFilter {

    /** Druid 控制台 common.js 的类路径（存在才处理） */
    private static final String COMMON_JS_CLASSPATH = "support/http/resources/js/common.js";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 先放行，让 Druid 的 common.js 正常走到响应阶段
        chain.doFilter(request, response);

        // 如果响应已提交，无法再改写，直接返回
        if (response.isCommitted()) {
            return;
        }

        // 类路径下不存在该资源 => 未引入 Druid 或版本不匹配，直接不处理
        ClassPathResource resource = new ClassPathResource(COMMON_JS_CLASSPATH);
        if (!resource.exists()) {
            return;
        }

        // 读取原始 common.js
        String text = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        // 去广告（与原逻辑一致）
        text = text.replaceAll("<a.*?banner\"></a><br/>", "");
        text = text.replaceAll("powered.*?shrek.wang</a>", "");

        // 仅清空缓冲区数据（不动响应头），并写回改写后的内容
        response.resetBuffer();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/javascript;charset=UTF-8");
        response.getWriter().write(text);
    }
}