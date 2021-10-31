package com.example.test.dubbo.cosurmer;


import com.alibaba.dubbo.config.annotation.Reference;
import com.perfma.xcenter.facade.ClientRpcService;
import com.perfma.xcenter.facade.dto.ClientInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *  查询客户端状态
 *
 * @author shenyuan
 * @version v0.1 2020/08/09 12:18
 */
@Slf4j
@Component
public class ClientRpcServiceCall {

    /**
     * 查询客户端状态
     */
    //"dubbo://localhost:20880"
    @Reference(retries = 0, timeout = 50000, check = false )
    ClientRpcService   clientRpcService;


    public ClientInfoDto getClientInfo(){
      return   clientRpcService.getClientInfo("10.10.220.36@10335-67ffa810");
    }

    public List<ClientInfoDto> getClientList(){
        List<String>  clientIds = new ArrayList<>();
        clientIds.add("10.10.220.36@10335-67ffa810");
        return   clientRpcService.getClientList(clientIds);
    }
}
