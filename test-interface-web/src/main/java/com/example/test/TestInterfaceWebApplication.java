package com.example.test;


import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.deepoove.swagger.dubbo.annotations.EnableDubboSwagger;
import com.example.test.congfig.MyBatisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@ComponentScan({"com.example.test.**"})
@EnableDubbo
@DubboComponentScan(basePackages = "com.example.test.*")
@SpringBootApplication
@EnableSwagger2
@ServletComponentScan(basePackages = {"com.example.test.filter"})
@MapperScan("com.example.test.*")
@Import({MyBatisConfig.class})
@EnableDubboSwagger
public class TestInterfaceWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestInterfaceWebApplication.class, args);
    }

}
