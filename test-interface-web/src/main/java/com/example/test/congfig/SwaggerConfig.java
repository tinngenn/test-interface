package com.example.test.congfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.test"))

                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Http测试接口汇总")
                .description("Http测试接口{ Http端口:8092(HTTP1.1) , Https端口:8091(HTTP2) }")
                .version("1.0")
                .build();
    }

}