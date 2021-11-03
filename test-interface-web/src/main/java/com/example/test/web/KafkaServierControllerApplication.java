package com.example.test.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.test.common.Constants;
import com.example.test.service.emum.DispatchErrorEnum;
import com.example.test.service.emum.ExeEventEnum;
import com.example.test.service.emum.ExecuteStatusEnum;
import com.example.test.service.kafka.FailureCallbackOverride;
import com.example.test.service.kafka.SuccessCallbackOverride;
import com.example.test.service.timetask.TimeTaskServeice;
import com.example.test.service.timetask.utils.KafkaMessage;
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
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @Autowired
    private KafkaMessage kafkaMessage;

    @Autowired
    TimeTaskServeice    timeTaskServeice;

    @ApiOperation("Http Kafka测试方法  InfoEvent 空闲 topic：tocean_infoEvent")
    @ApiImplicitParams(
            {
              @ApiImplicitParam(name = "message", value = "用例ID", required = false , dataType = "string")
            })

    // 发送消息
    @RequestMapping( value = "/kafka/infoEvent/free/start" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendInfoEventFreeStart(HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);

        /*Date date = new Date();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String format = bf.format(date)*/;


        if(parmMap.containsKey("clientId") && StringUtils.isNotEmpty(parmMap.get("clientId"))){
            String clientId = parmMap.get("clientId");
            timeTaskServeice.addTask(clientId,false);

            return  "InfoEvent 发送成功";
        }
        else {return "clientId不能为null"; }

    }


    @ApiOperation("Http Kafka测试方法  InfoEvent 空闲 topic：tocean_infoEvent")
    // 发送消息
    @RequestMapping( value = "/kafka/infoEvent/free/stop" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendInfoEventFreeStop(HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);

      return   timeTaskServeice.cancelTask();
    }



    @ApiOperation("Http Kafka测试方法  InfoEvent 繁忙 topic：tocean_infoEvent")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "message", value = "用例ID", required = false , dataType = "string")
            })

    // 发送消息
    @RequestMapping( value = "/kafka/infoEvent/busy/start" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendInfoEventBusyStart(HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);

        /*Date date = new Date();
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String format = bf.format(date)*/;


        if(parmMap.containsKey("clientId") && StringUtils.isNotEmpty(parmMap.get("clientId"))){
            String clientId = parmMap.get("clientId");
            timeTaskServeice.addTask(clientId,true);

            return  "InfoEvent  发送成功";
        }
        else {return "clientId不能为null"; }

    }


    @ApiOperation("Http Kafka测试方法  InfoEvent 繁忙  topic：tocean_infoEvent")
    // 发送消息
    @RequestMapping( value = "/kafka/infoEvent/busy/stop" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendInfoEventBusyStop(HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);

        return   timeTaskServeice.cancelTask();
    }





    @ApiOperation("Http Kafka测试方法  定时任务事件  topic：tocean_timedEvent")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "message", value = "用例ID", required = false , dataType = "string")
            })

    // 发送消息
    @RequestMapping( value = "/kafka/dispatchSuccessEvent" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendMessage2(HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);

        if (parmMap.containsKey("clientId") && StringUtils.isNotEmpty(parmMap.get("clientId"))) {
            String clientId = parmMap.get("clientId");

            if (parmMap.containsKey("requestId") && StringUtils.isNotEmpty(parmMap.get("requestId"))) {
                String requestId = parmMap.get("requestId");
                String message = kafkaMessage.getReportMessageToJson(ExeEventEnum.dispatch,clientId,
                        kafkaMessage.getDispatchSuccessEvent(requestId));
                ListenableFuture listenableFuture = kafkaTemplate.send(Constants.TOCEAN_TIMED_EVENT, message);
                listenableFuture.addCallback(successCallbackOverride, failureCallbackOverride);
                return "DispatchSuccessEvent 发送成功";
            }
        }
        return "clientId和requestId不能为null";
    }


    // 发送消息
    @RequestMapping( value = "/kafka/dispatchDoneEvent" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendMessage3(HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap =  cotrollerFuction.printMessage(httpRequest);
        try {
            if (parmMap.containsKey("clientId") && StringUtils.isNotEmpty(parmMap.get("clientId"))) {
                String clientId = parmMap.get("clientId");

                if (parmMap.containsKey("requestId") && StringUtils.isNotEmpty(parmMap.get("requestId"))) {
                    String requestId = parmMap.get("requestId");

                    if (parmMap.containsKey("executeStatusEnums") &&
                            StringUtils.isNotEmpty(parmMap.get("executeStatusEnums"))) {
                        String executeStatusEnums = parmMap.get("executeStatusEnums");
                        log.info(executeStatusEnums);
                        List<ExecuteStatusEnum> students = JSON.parseArray(executeStatusEnums, ExecuteStatusEnum.class);
                        String message = kafkaMessage.getReportMessageToJson(ExeEventEnum.dispatch, clientId,
                                kafkaMessage.getDispatchDoneEvent(requestId, students));
                        ListenableFuture listenableFuture = kafkaTemplate.send(Constants.TOCEAN_TIMED_EVENT, message);
                        listenableFuture.addCallback(successCallbackOverride, failureCallbackOverride);
                        return "DispatchDoneEvent 发送成功";

                    }

                }
            }
            return "clientId和requestId不能为null";
        }catch (Exception e){
            return  "执行异常" + e;
        }
    }

    //发送定时任务错误
    @RequestMapping( value = "/kafka/dispatchErrorEvent" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendDispatchErrorEvent(HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);
        try {
            if (parmMap.containsKey("clientId") && StringUtils.isNotEmpty(parmMap.get("clientId"))) {
                String clientId = parmMap.get("clientId");

                if (parmMap.containsKey("requestId") && StringUtils.isNotEmpty(parmMap.get("requestId"))) {
                    String requestId = parmMap.get("requestId");

                    if (parmMap.containsKey("dispatchErrorEnum") &&
                            StringUtils.isNotEmpty(parmMap.get("dispatchErrorEnum"))) {
                        String dispatchErrorEnum = parmMap.get("dispatchErrorEnum");
                        log.info(dispatchErrorEnum);
                        DispatchErrorEnum ErrorEnum = JSONObject.parseObject(dispatchErrorEnum, DispatchErrorEnum.class);
                        String message = kafkaMessage.getReportMessageToJson(ExeEventEnum.dispatch, clientId,
                                kafkaMessage.getDispatchErrorEvent(requestId, ErrorEnum));
                        ListenableFuture listenableFuture = kafkaTemplate.send(Constants.TOCEAN_TIMED_EVENT, message);
                        listenableFuture.addCallback(successCallbackOverride, failureCallbackOverride);
                        return "DispatchErrorEvent 发送成功";
                    }

                }
            }
            return "clientId和requestId不能为null";
        }catch (Exception e){

            return  "执行异常" + e;
        }
    }

}
