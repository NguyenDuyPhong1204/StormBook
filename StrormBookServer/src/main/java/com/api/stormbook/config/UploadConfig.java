package com.api.stormbook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map URL /uploads/** -> thư mục thật trên máy bạn
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
        // "file:" + đường dẫn thư mục uploads (nằm ngang hàng với thư mục project hoặc bạn tự chỉ định)
    }
}
