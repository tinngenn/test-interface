package com.example.test.service;

import com.alibaba.fastjson.JSON;
import com.example.test.service.resultDTO.InfoEvent;
import io.socket.client.IO;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketIoProxy {

    private Socket socket;

    public void initSocket() {
        try {
            String executorUrl = "ws://192.168.100.124:6419";
            Map<String, String> auth = new HashMap<>(2);
            auth.put("type", "token");
            auth.put("token", UUID.randomUUID().toString());
            String[] transports = { "websocket" };
            IO.Options options = IO.Options.builder().setTransports(transports).setTimeout(-1L).setAuth(auth).build();
            this.socket = IO.socket(executorUrl, options);
            this.socket.on("connect", args -> log.info("[执行机连接成功]"));
            this.socket.on("disconnect", args -> log.info("[执行机连接断开]"));
            this.socket.on("connect_error", args -> {
                Exception e = (args.length > 0) ? (Exception)args[0] : null;
                log.error("[执行机连接错误]:{}",e);
            });
            onInfo();
            this.socket.open();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeSocket() {


        log.info("关闭");
        this.socket.close();
    }

 /*   dispatch("dispatch", "),
             info("info", "),
             message("message", "),
             query("query", ");*/

    public void onInfo() {
        this.socket.on("info", args -> {
            JSONObject json = (args.length > 0) ? (JSONObject)args[0] : null;
            if (json == null) {
                log.error("info - 收到了空事件" );
            } else {
                if (log.isDebugEnabled())
                    log.debug("[{}", json);
                InfoEvent infoEvent = JSON.parseObject(json.toString(), InfoEvent.class);
                log.info("infoEvent:{}",infoEvent.toString());
            }
        });
    }



}

