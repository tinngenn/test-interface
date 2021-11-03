package com.example.test.congfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 继承 ThreadPoolTaskScheduler
 * <p>Title: CustomTaskScheduler</p>
 * <p>Description: TODO(类注释)</p>
 * @author hfuquan
 * @date 2020年11月30日  下午17:46:52
 * @since JDK1.8
 */
@Service
@Slf4j
public class CustomTaskScheduler extends ThreadPoolTaskScheduler {

    private static final long serialVersionUID = 1L;

    private final Map<Object, ScheduledFuture<?>> scheduledTasks = new IdentityHashMap<>();
    @Nullable
    private volatile ErrorHandler errorHandler;

    /**
     * <p>Title: cancelTask</p>
     * <p>Description: 取消定时器执行</p>
     * @param identifier scheduledTasks.put 的值
     * @author hfuquan
     * @Date 2020年9月11日 上午11:37:06
     */
    void cancelTask(Object identifier) {
        ScheduledFuture<?> future = scheduledTasks.get(identifier);
        if (null != future) {
            future.cancel(true);
        }
    }

    /**
     * 使用 @Scheduled(fixedRate = xxL) 时，项目启动时，会自动调用这个方法，
     * 一个类同时有多个@Scheduled，所产生的
     * call parent method and store the result Future for cancel task,
     * you can expand other method of you used.
     *
     * @param task   the task need to be executed
     * @param period the time between two continues execute
     * @return the result of task
     */
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        ScheduledFuture<?> future = super.scheduleAtFixedRate(task, period);
        scheduledTasks.put(ProcessorTask.CANCEL_TASK_METHOD_NAME, future);
        return future;
    }


    /**
     * 使用 @Scheduled(fixedDelay = xxL) 时，项目启动时，会自动调用这个方法
     * <p>Title: scheduleWithFixedDelay</p>
     * <p>Description: </p>
     * @param task 如：com.phnix.task.ProcessorTask.doFixedDelay
     * @param delay
     * @return
     * @see org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler#scheduleWithFixedDelay(Runnable, long)
     */
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        ScheduledExecutorService executor = getScheduledExecutor();
        try {
            ScheduledFuture<?> future = executor.scheduleWithFixedDelay(errorHandlingTask(task, true), 0, delay, TimeUnit.MILLISECONDS);
            scheduledTasks.put(ProcessorTask.CANCEL_TASK_METHOD_NAME_2, future);
            return future;
        }
        catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }
    private Runnable errorHandlingTask(Runnable task, boolean isRepeatingTask) {
        return TaskUtils.decorateTaskWithErrorHandler(task, this.errorHandler, isRepeatingTask);
    }

}