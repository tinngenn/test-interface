package com.example.test.dubbo.exchanger;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.Transporters;
import com.alibaba.dubbo.remoting.exchange.ExchangeClient;
import com.alibaba.dubbo.remoting.exchange.ExchangeHandler;
import com.alibaba.dubbo.remoting.exchange.ExchangeServer;
import com.alibaba.dubbo.remoting.exchange.Exchanger;
import com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeClient;
import com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeServer;
import com.alibaba.dubbo.remoting.transport.DecodeHandler;
import com.example.test.dubbo.channelHandler.RpcMockExchangeHandler;
import com.example.test.dubbo.codec.RpcMockCodec;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class RpcMockExchanger implements Exchanger {

    @Override
    public ExchangeServer bind(URL url, ExchangeHandler exchangeHandler) throws RemotingException {

        url = url.addParameter("codec", RpcMockCodec.MOCK_CODEC_NAME);
        return new HeaderExchangeServer(Transporters.bind(url, new DecodeHandler(new RpcMockExchangeHandler(exchangeHandler))));
    }

    @Override
    public ExchangeClient connect(URL url, ExchangeHandler exchangeHandler) throws RemotingException {

        return new HeaderExchangeClient(Transporters.connect(url, new DecodeHandler(new RpcMockExchangeHandler(exchangeHandler))),
                true);
    }
}
