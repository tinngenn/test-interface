package com.example.test.dubbo.provider;



import com.alibaba.dubbo.config.annotation.Service;
import com.example.test.dubbo.api.VirtualMockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Service( timeout = 5000 ,  interfaceClass = VirtualMockService.class)
public class VirtualMockServiceImpl implements VirtualMockService {

    @Override
    public String virtualMethod() {
        return "Mock Service Provider";
    }
}
