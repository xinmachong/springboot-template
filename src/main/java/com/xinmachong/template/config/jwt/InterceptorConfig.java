package com.xinmachong.template.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author meyer@HongYe
 * 对所有请求的路由进行判断是否放行
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private JWTProperties jwtProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePaths = jwtProperties.getExcludePaths();
        if (excludePaths.size() != 0) {
            registry.addInterceptor(new JWTInterceptor())
                    .addPathPatterns("/**")
                    .excludePathPatterns(excludePaths);
        } else {
            registry.addInterceptor(new JWTInterceptor())
                    .addPathPatterns("/**");
        }
    }
}
