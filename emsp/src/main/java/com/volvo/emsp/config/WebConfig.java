package com.volvo.emsp.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final UserAuthService userAuthService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TenantInterceptor(userAuthService))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/caches/**",
                        "/doc.html",
                        "/webjars/**",
                        "/img.icons/**",
                        "/swagger-resources/**",
                        "/favicon.ico",
                        "/v2/api-docs"
                );
    }
}
