package com.sduwh.onlineBills.config

import com.sduwh.onlineBills.interceptor.LoginInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
open class InterceptorConfig: WebMvcConfigurationSupport() {
    override fun addInterceptors(registry: InterceptorRegistry?) {
        registry!!.addInterceptor(LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/error")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.ttf")
                .excludePathPatterns("/**/*.woff")
                .excludePathPatterns("/**/*.woff2")
                .excludePathPatterns("/**/favicon.ico")
        super.addInterceptors(registry)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler("/**/*.js").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("/**/*.css").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("/**/*.ttf").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("/**/*.woff").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("/**/*.woff2").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("/**/favicon.ico")
        super.addResourceHandlers(registry)
    }
}