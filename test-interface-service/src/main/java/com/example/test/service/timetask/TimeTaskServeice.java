package com.example.test.service.timetask;


import com.example.test.common.Constants;
import com.example.test.service.timetask.run.InfoEventTaskRun;
import com.example.test.service.timetask.taskservice.InfoEventKafkaTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *  批量任务处理服务
 *
 * @author shenyuan
 * @version v0.1 2020/12/01 17:18
 */

@Slf4j
@Service
public class TimeTaskServeice {

    //定时任务注册处理
    @Autowired
    TaskRunnableAction         taskRunnableAction;

    @Autowired
    InfoEventKafkaTask  infoEventKafkaTask;

    public String addTask(String clientId ,Boolean isBusy){
        log.info(clientId);
        try{
                InfoEventTaskRun infoEventTaskRun = new InfoEventTaskRun();
                infoEventTaskRun.setClientId(clientId);
                infoEventTaskRun.setIsBusy(isBusy);
                infoEventTaskRun.setInfoEventTaskRun(infoEventKafkaTask);
                taskRunnableAction.add(infoEventTaskRun, Constants.InfoEventKafka,"0/10 * * * * ?");

        } catch (Exception e) {
            log.info("停止批量任务系统异常:{}",e);
            return "异常";
        }

        return  "异常";
    }



    public String cancelTask(){

        try{

            taskRunnableAction.stop(Constants.InfoEventKafka);
        } catch (Exception e) {
            log.info("停止批量任务系统异常:{}",e);
            return "异常";
        }

        return  Constants.InfoEventKafka + "停止";

    }


}
