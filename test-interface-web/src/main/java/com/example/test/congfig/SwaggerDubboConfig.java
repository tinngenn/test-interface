package com.example.test.congfig;

import com.deepoove.swagger.dubbo.annotations.EnableDubboSwagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;

/**
 * @author cdfive
 */
@Configuration
@EnableDubboSwagger
public class SwaggerDubboConfig  {

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider(Environment environment, DocumentationCache documentationCache) {
        return new CustormSwaggerResourcesProvider(environment, documentationCache);
    }

    static class CustormSwaggerResourcesProvider extends InMemorySwaggerResourcesProvider {

        public CustormSwaggerResourcesProvider(Environment environment, DocumentationCache documentationCache) {
            super(environment, documentationCache);
        }

        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> swaggerResources = super.get();

            SwaggerResource swaggerDubboResource = new SwaggerResource();
            swaggerDubboResource.setName("dubbo");
            swaggerDubboResource.setLocation("/swagger-dubbo/api-docs");
            swaggerDubboResource.setSwaggerVersion("2.0");
            swaggerResources.add(swaggerDubboResource);
            return swaggerResources;
        }
    }
}