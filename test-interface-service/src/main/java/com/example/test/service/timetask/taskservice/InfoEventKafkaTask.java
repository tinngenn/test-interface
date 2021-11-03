package com.example.test.service.timetask.taskservice;


import com.example.test.common.Constants;
import com.example.test.service.emum.ExeEventEnum;
import com.example.test.service.kafka.FailureCallbackOverride;
import com.example.test.service.kafka.SuccessCallbackOverride;
import com.example.test.service.timetask.utils.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
public class InfoEventKafkaTask {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaMessage kafkaMessage;

    @Autowired
    private SuccessCallbackOverride successCallbackOverride;

    @Autowired
    private FailureCallbackOverride failureCallbackOverride;

    public void sendInfoEventKafka(String clientId,Boolean isBusy){

        String message = kafkaMessage.getReportMessageToJson(ExeEventEnum.info,clientId ,
                                                                kafkaMessage.getInfoEvent(isBusy));
        ListenableFuture listenableFuture = kafkaTemplate.send(Constants.TOCEAN_INFO_EVENT, message);
        listenableFuture.addCallback(successCallbackOverride,failureCallbackOverride);


    }
}
