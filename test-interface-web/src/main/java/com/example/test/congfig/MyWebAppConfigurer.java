package com.example.test.congfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    /**上传地址*/
    private String filePath="/upload/**";
    /**显示相对地址*/
    private String fileRelativePath="./fileUpload/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fileUpload/**").addResourceLocations("file:" + fileRelativePath);
    }
}
