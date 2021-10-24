package com.example.test.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.SuccessCallback;

@Slf4j
@Component
public class SuccessCallbackOverride implements SuccessCallback {
    
    @Override
    public void onSuccess(Object o) {
        log.info("发送内容:{} 发送成功", o.toString());
    }
}
