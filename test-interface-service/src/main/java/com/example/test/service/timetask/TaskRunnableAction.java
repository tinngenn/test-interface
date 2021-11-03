package com.example.test.service.timetask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

/**
 *  线程任务操作，新增，删除 和查询，定时任务线程
 *
 * @author shenyuan
 * @version v0.1 2020/12/01 17:18
 */

@Slf4j
@Service
public class TaskRunnableAction {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private Map<String, ScheduledFuture<?>> taskMap = new HashMap<>();

    public boolean add(Runnable taskRunnable, String name, String cron) throws Exception {

        if(null != taskMap.get(name)) {
            return false;
        }
        ScheduledFuture<?> schedule
                = threadPoolTaskScheduler.schedule(taskRunnable, new CronTrigger(cron));
        taskMap.put(name, schedule);
        return true;
    }


    public boolean stop(String name) throws Exception{
        if(null == taskMap.get(name)) {
            return false;
        }
        ScheduledFuture<?> scheduledFuture = taskMap.get(name);
        scheduledFuture.cancel(true);
        taskMap.remove(name);
        return true;
    }

    public String listTask(){

        return taskMap.keySet().stream().map(name -> name).collect(Collectors.joining(","));

    }
}