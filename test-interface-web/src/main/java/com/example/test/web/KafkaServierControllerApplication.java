package com.example.test.web;

import com.example.test.common.Constants;
import com.example.test.dal.service.TestCaseDefineApiAutoService;
import com.example.test.dal.service.TestCaseDefineApiAutoStepService;
import com.example.test.dal.service.TestCaseService;
import com.example.test.dubbo.api.dubboResult.Result;
import com.example.test.service.ApiOptionService;
import com.example.test.service.CreateTestCaseService;
import com.example.test.service.kafka.FailureCallbackOverride;
import com.example.test.service.kafka.SuccessCallbackOverride;
import com.example.test.utils.CotrollerFuction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.ThreadPoolUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@SpringBootApplication
@Api(tags = "Kafka 操作接口类")
public class KafkaServierControllerApplication {

    @Autowired
    CotrollerFuction cotrollerFuction;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private SuccessCallbackOverride successCallbackOverride;

    @Autowired
    private FailureCallbackOverride failureCallbackOverride;

    @ApiOperation("Http Kafka测试方法  topic：testtopic")
    @ApiImplicitParams(
            {
              @ApiImplicitParam(name = "message", value = "用例ID", required = false , dataType = "string")
            })

    // 发送消息
    @RequestMapping( value = "/kafka" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendMessage1(HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);

        Date date = new Date();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String format = bf.format(date);

        String message = "";

        if(parmMap.containsKey("message") && StringUtils.isNotEmpty(parmMap.get("message"))){
            message = parmMap.get("message");
            ListenableFuture listenableFuture = kafkaTemplate.send(Constants.TEST_TPOIC, message);

            listenableFuture.addCallback(successCallbackOverride,failureCallbackOverride);
        }

        return  "1111";
    }

}
