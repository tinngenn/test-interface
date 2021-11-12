package com.example.test.service.timetask.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.service.dto.*;
import com.example.test.service.emum.DispatchErrorEnum;
import com.example.test.service.emum.ExeEventEnum;
import com.example.test.service.emum.ExecuteStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Component
public class KafkaMessage {
    //执行机信息
    public InfoEvent getInfoEvent(Boolean isBusy){

        InfoEvent infoEvent = new InfoEvent();
        infoEvent.setVersion("0.0.13");
        infoEvent.setCommit("48c2d08");
        infoEvent.setNode("v16.5.0");
        infoEvent.setPlatform("Linux x64 3.10.0-1127.10.1.el7.x86_64");
        infoEvent.setV8("9.1.269.38-node.20");
        infoEvent.setOpenssl("1.1.1k+quic");
        infoEvent.setUsername("admin");
        infoEvent.setPid(3015L);
        infoEvent.setUid(1000L);
        infoEvent.setCpus(16L);
        infoEvent.setTotalmem(8198979584L);
        infoEvent.setFreemem(5225357312L);
        infoEvent.setMaxWorker(22L);
        infoEvent.setIdleWorker(22L);
        infoEvent.setIsBusy(isBusy);
        infoEvent.setStartTime(1635615722119l);
        infoEvent.setTmpdir("/tmp");
        return  infoEvent;
    }

    //Kafaka消息体
    public  String getReportMessageToJson(ExeEventEnum exeEventEnum, String clientId, Object date){
        ReportMessage reportMessage = new ReportMessage();
        ClientInfo clientInfo =new ClientInfo();
        clientInfo.setClientId(clientId);
        clientInfo.setClientType("ToceanXengine");
        clientInfo.setNodeName("10.10.20.199:17950");
        clientInfo.setOrgId(1L);
        clientInfo.setAppId(377820929843134464l);


        reportMessage.setData(date);
        Calendar calendar =  Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, (calendar.get(Calendar.HOUR_OF_DAY)));
        reportMessage.setClientInfo(clientInfo);
        reportMessage.setReportTime(calendar.getTime());
        reportMessage.setEvent(exeEventEnum);
        return  JSONObject.toJSONString(reportMessage,
                SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }


    //定时任务事件返回
    public String getDispatchSuccessEvent(String requestId){
        DispatchSuccessEvent dispatchSuccessEvent = new DispatchSuccessEvent();
        dispatchSuccessEvent.setEvent("success");
        dispatchSuccessEvent.setRequestId(requestId);
        return  JSONObject.toJSONString(dispatchSuccessEvent, SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }

    //定时任务事件返回
    public String getDispatchDoneEvent(String requestId,List<ExecuteStatusEnum> executeStatusEnums)
                                                                    throws JsonProcessingException {
        DispatchDoneEvent dispatchDoneEvent = new DispatchDoneEvent();
        dispatchDoneEvent.setEvent("done");
        dispatchDoneEvent.setRequestId(requestId);
        dispatchDoneEvent.setData(executeStatusEnums);
        return  new ObjectMapper().writeValueAsString(dispatchDoneEvent);
    }

    //定时任务事件返回
    public String getDispatchErrorEvent(String requestId,DispatchErrorEnum dispatchErrorEnum)
            throws JsonProcessingException {
        DispatchErrorEvent dispatchErrorEvent = new DispatchErrorEvent();
        dispatchErrorEvent.setEvent("error");//done, error, success
        dispatchErrorEvent.setRequestId(requestId);
        DispatchError dispatchError = new DispatchError();
        dispatchError.setCode(dispatchErrorEnum);
        dispatchError.setMessage(dispatchErrorEnum.getDesc());
        dispatchErrorEvent.setData(dispatchError);
        return   new ObjectMapper().writeValueAsString(dispatchErrorEvent);
    }


    @Autowired
    ObjectMapper objectMapper;
    //定时任务事件返回
    public String getMessageDoneEvent(MessageDoneEventDTO messageDoneEventDTO)
            throws JsonProcessingException {
        MessageDoneEvent messageDoneEvent = new MessageDoneEvent();
        messageDoneEvent.setId(messageDoneEventDTO.getTestCaseId());
        messageDoneEvent.setEvent("done");
        messageDoneEvent.setRequestId(messageDoneEventDTO.getRequestId());
        messageDoneEvent.setExecuteId(messageDoneEventDTO.getExecuteId());
        messageDoneEvent.setFileKey(messageDoneEventDTO.getFileKey());
        messageDoneEvent.setBytesSize(6666);
        messageDoneEvent.setFileName(messageDoneEventDTO.getTestCaseName());
        messageDoneEvent.setContentType("application/octet-stream");
        messageDoneEvent.setStatus(messageDoneEvent.getStatus());
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        messageDoneEvent.setStartTime(LocalDateTime.parse(messageDoneEventDTO.getStartTime(),sdf));
        messageDoneEvent.setEndTime(LocalDateTime.parse(messageDoneEventDTO.getEndTime(),sdf));
        messageDoneEvent.setName(messageDoneEventDTO.getTestCaseName());
        messageDoneEvent.setId(messageDoneEventDTO.getTestCaseId());
        messageDoneEvent.setStepCount(Integer.valueOf(messageDoneEventDTO.getStepCount()));
       // messageDoneEvent.setPriority(Integer.valueOf(messageDoneEventDTO.getPriority()));
        messageDoneEvent.setStatus(messageDoneEventDTO.getExecuteStatusEnum());
        return   objectMapper.writeValueAsString(messageDoneEvent);
    }
}
