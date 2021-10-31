package com.example.test.dubbo.channelHandler;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.exchange.ExchangeChannel;
import com.alibaba.dubbo.remoting.exchange.ExchangeHandler;
import com.alibaba.dubbo.remoting.exchange.Request;
import com.alibaba.dubbo.remoting.exchange.Response;
import com.alibaba.dubbo.remoting.exchange.support.DefaultFuture;
import com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeHandler;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Map;


@Slf4j
public class RpcMockExchangeHandler extends HeaderExchangeHandler {

//    private static final Map<Integer, String> mockGenericTypeMap = new ConcurrentHashMap<>();


    private final ExchangeHandler handler;

    public RpcMockExchangeHandler(ExchangeHandler handler) {
        super(handler);
        if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        this.handler = handler;
    }

//    public static Map<Integer, String> getMockGenericTypeMap() {
//        return mockGenericTypeMap;
//    }

    @Override
    public void received(Channel channel, Object message) throws RemotingException {
        log.info("tttttttttttttttttttttttttttttttttt");

        channel.setAttribute(KEY_READ_TIMESTAMP, System.currentTimeMillis());
//        ExchangeChannel exchangeChannel = HeaderExchangeChannel.getOrAddChannel(channel);
        ExchangeChannel exchangeChannel = (ExchangeChannel) channel.getAttribute("com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeChannel.CHANNEL");
        try {
            log.info("1221212111:{}",message instanceof Request);
            log.info("1221212112:{}",message.getClass().getName());
            if (message instanceof Request) {
                // handle request.
                Request request = (Request) message;
                log.info("1221212111:{}",request.toString());
                if (request.isEvent()) {
                    handlerMockEvent(channel, request);
                } else {
                    if (request.isTwoWay()) {
                        Response response = handleMockRequest(exchangeChannel, request);
                        log.info("nnnnnnnnnnnnnnnnnnnnnnnnn");
                        mock(request, response);
                        channel.send(response);
                    } else {
                        handler.received(exchangeChannel, request.getData());
                    }
                }
            } else if (message instanceof Response) {
                handleResponse(channel, (Response) message);
            } else if (message instanceof String) {
                if (isClientSide(channel)) {
                    Exception e = new Exception("Dubbo client can not supported string message: " + message + " in channel: " + channel + ", url: " + channel.getUrl());
                    logger.error(e.getMessage(), e);
                } else {
                    String echo = handler.telnet(channel, (String) message);
                    if (echo != null && echo.length() > 0) {
                        channel.send(echo);
                    }
                }
            } else {
                log.info("mmmmmmmmmmmmmmmmmmmmmmmmmm");

                handler.received(exchangeChannel, message);
            }
        } finally {
//            HeaderExchangeChannel.removeChannelIfDisconnected(channel);
            if (channel != null && !channel.isConnected()) {
                channel.removeAttribute("com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeChannel.CHANNEL");
            }
        }
    }

    private void mock(Request request, Response response) {
         log.info("request{}",request.toString());
         log.info("response{}",response.toString());
        if (
//                response.getErrorMessage().startsWith("com.alibaba.dubbo.remoting.RemotingException: Not found exported service: ")
//                &&
                response.getStatus() == Response.SERVICE_ERROR) {
            log.info("开始执行rpcmock服务，接收到的数据：{}", request.getData());
            Object param = request.getData();

            String jsonStr= "{\"result\":{\"actionUrl\":\"http://1111222333\",\"bankRespCode\":\"BKdatafactory\",\"bankRespDesc\":\"交易成功2\",\"payRespDesc\":\"交易成功2\",\"payRespNo\":\"11223344\",\"realRtn\":true,\"status\":\"B\",\"values\":{\"111\":\"222\"}},\"success\":true} ";
            Map<String, Object> mockResultMap = JSON.parseObject(jsonStr, Map.class);

            RpcResult rpcResult = new RpcResult(mockResultMap);


            response.setResult(rpcResult);
            response.setStatus(Response.OK);

        }
    }

    static void handleResponse(Channel channel, Response response) throws RemotingException {
        if (response != null && !response.isHeartbeat()) {
            DefaultFuture.received(channel, response);
        }
    }

    Response handleMockRequest(ExchangeChannel channel, @NonNull Request req) throws RemotingException {
        Response res = new Response(req.getId(), req.getVersion());
        if (req.isBroken()) {
            Object data = req.getData();

            String msg;
            if (data == null) msg = null;
            else if (data instanceof Throwable) msg = StringUtils.toString((Throwable) data);
            else msg = data.toString();
            res.setErrorMessage("Fail to decode request due to: " + msg);
            res.setStatus(Response.BAD_REQUEST);

            return res;
        }
        // find handler by message class.
        Object msg = req.getData();
        try {
            // handle data.
            Object result = handler.reply(channel, msg);
            res.setStatus(Response.OK);
            res.setResult(result);
        } catch (Throwable e) {
            res.setStatus(Response.SERVICE_ERROR);
            res.setErrorMessage(StringUtils.toString(e));
        }
        return res;
    }

    void handlerMockEvent(Channel channel, Request req) throws RemotingException {
        if (req.getData() != null && req.getData().equals(Request.READONLY_EVENT)) {
            channel.setAttribute(Constants.CHANNEL_ATTRIBUTE_READONLY_KEY, Boolean.TRUE);
        }
    }

    private static boolean isClientSide(Channel channel) {
        InetSocketAddress address = channel.getRemoteAddress();
        URL url = channel.getUrl();
        return url.getPort() == address.getPort() &&
                NetUtils.filterLocalHost(url.getIp())
                        .equals(NetUtils.filterLocalHost(address.getAddress().getHostAddress()));
    }
}
