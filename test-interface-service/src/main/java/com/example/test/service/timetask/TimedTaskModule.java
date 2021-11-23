package com.example.test.service.timetask;


import com.example.test.service.SocketIoProxy;
import com.example.test.service.farend.CommandModule;
import com.perfma.xcenter.client.command.Command;
import com.perfma.xcenter.client.command.Module;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Module("TIMED_TASK")
public class TimedTaskModule implements CommandModule {


    @Command(name = "DISPATCH")
    public String dispatch(String params) {
       log.info("2222222222222222222222222");
        return "success";
    }

    @Command(name = "CANCEL")
    public String cancel(String params) {
        log.info("3333333333333333333333");
        return "cancel";
    }
/*
    public void dealMessageEvent(AbstractMessageEvent event) {

    }

    public void dealDispatchEvent(AbstractDispatchEvent event) {

    }*/
}