package com.example.test.congfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProcessorTask {
    //给每个方法定义一个常量名字
    public static final String CANCEL_TASK_METHOD_NAME="doFixedRate";
    public static final String CANCEL_TASK_METHOD_NAME_2="doFixedDelay";
    private static final int MAX_COUNTS = 5;
    private int counts = 0;

    @Autowired
    private CustomTaskScheduler scheduler;

    /**
     * This task will be execute file times and then cancel.
     */
    //@Scheduled(fixedRate = 900L)
    public void doFixedRate() {
        //判断条件、业务处理 根据自己需要做修改
        if (counts >= MAX_COUNTS) {
            //cancelTask的值必须和CustomTaskScheduler类 scheduleAtFixedRate()方法 中scheduledTasks.put 的key一样，否则无法取消定时任务
            scheduler.cancelTask(CANCEL_TASK_METHOD_NAME);
        }
        System.out.println("this " + counts++ + " times do processor task doFixedRate");
    }

    //@Scheduled(fixedDelay = 1000L)
    public void doFixedDelay() {
        if (counts >= MAX_COUNTS+5) {
            System.out.println("processor task success,begin to cancel doFixedDelay");
            scheduler.cancelTask(CANCEL_TASK_METHOD_NAME_2);
        }
        System.out.println("this " + counts++ + " times do processor task doFixedDelay");
    }

}