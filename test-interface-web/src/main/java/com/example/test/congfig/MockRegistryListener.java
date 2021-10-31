package com.example.test.congfig;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Slf4j
@Configuration
public class MockRegistryListener implements ApplicationListener<ApplicationEvent> {



    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            log.info("开始注册mock服务");
            try{

            }catch (Exception e){
                log.error("重新注册mock服务出错",e);
            }
        }
    }
}
