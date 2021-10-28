package com.example.test.web;

import com.example.test.common.Constants;
import com.example.test.dubbo.cosurmer.CommandRpcServiceCall;
import com.example.test.service.SocketIoProxy;
import com.example.test.utils.CotrollerFuction;
import com.perfma.xcenter.facade.dto.CommandResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Controller
@SpringBootApplication
@Api(tags = "Kafka 操作接口类")
public class WebSocketControllerApplication {

    @Autowired
    CotrollerFuction cotrollerFuction;

    @Autowired
    SocketIoProxy socketIoProxy;

    @ApiOperation("Http WebSocket")
    @ApiImplicitParams(
            {
              @ApiImplicitParam(name = "message", value = "用例ID", required = false , dataType = "string")
            })

    // 发送消息
    @RequestMapping( value = "/WebSocket" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String WebSocket(HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);

        socketIoProxy.initSocket();
        //socketIoProxy.closeSocket();
        //socketIoProxy.onInfo();
       // socketIoProxy.onDispatch();
        return  "111";
    }

    @Autowired
    CommandRpcServiceCall commandRpcServiceCall;
    @RequestMapping( value = "/tttttt" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String ttttt(HttpServletRequest httpRequest) throws ExecutionException, InterruptedException {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);
        CompletableFuture<Map<String, CommandResult>>  result =commandRpcServiceCall.invoke();

        log.info("11111111111111111");
        log.info(result.get().toString());
        while (true){
            if(result.isDone()){
                log.info("执行完成");
                log.info(result.get().toString());
                break;
            }
        }
        //socketIoProxy.initSocket();
        //socketIoProxy.closeSocket();
        //socketIoProxy.onInfo();
        // socketIoProxy.onDispatch();
        return  "111";
    }


}
