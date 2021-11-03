package com.example.test.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;

/**
 * kafka 发送失败回掉
 *
 * @author sy
 * @version
 */
@Slf4j
@Component
public class FailureCallbackOverride implements FailureCallback {
    @Override
    public void onFailure(Throwable throwable) {
        log.info("发送内容:{} 发送失败", throwable.toString());
    }
}
