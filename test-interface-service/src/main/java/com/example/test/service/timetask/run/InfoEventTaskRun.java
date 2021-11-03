package com.example.test.service.timetask.run;

import com.example.test.common.Constants;
import com.example.test.service.emum.ExeEventEnum;
import com.example.test.service.kafka.FailureCallbackOverride;
import com.example.test.service.kafka.SuccessCallbackOverride;
import com.example.test.service.timetask.taskservice.InfoEventKafkaTask;
import com.example.test.service.timetask.utils.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;



/**
 *  心跳 InfoEvent kafka
 *
 * @author shenyuan
 * @version v0.1 2020/12/01 17:18
 */
@Slf4j
@Component
public class InfoEventTaskRun implements Runnable{

    private String clientId;

    private Boolean isBusy;

    private InfoEventKafkaTask infoEventKafkaTask;

   public void setInfoEventTaskRun (InfoEventKafkaTask infoEventKafkaTask){

        this.infoEventKafkaTask = infoEventKafkaTask;
    }

    public void setClientId(String clientId){
        this.clientId = clientId;
    }

    public void setIsBusy(Boolean isBusy){
        this.isBusy = isBusy;
    }

    @Override
    public void run() {
      try{
          log.info(clientId);

          infoEventKafkaTask.sendInfoEventKafka(clientId,isBusy);
        }catch (Exception e){ log.info("InfoEvent的kafka任务执行异常：{}",e);

        }

    }

}

