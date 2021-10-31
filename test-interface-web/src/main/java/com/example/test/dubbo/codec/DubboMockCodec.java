package com.example.test.dubbo.codec;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.io.Bytes;
import com.alibaba.dubbo.common.io.UnsafeByteArrayInputStream;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.exchange.Request;
import com.alibaba.dubbo.remoting.exchange.Response;
import com.alibaba.dubbo.remoting.transport.CodecSupport;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.protocol.dubbo.DecodeableRpcResult;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class DubboMockCodec extends DubboCodec {

    @Override
    protected Object decodeBody(Channel channel, InputStream is, byte[] header) throws IOException {
        byte flag = header[2], proto = (byte) (flag & SERIALIZATION_MASK);
        Serialization s = CodecSupport.getSerialization(channel.getUrl(), proto);
        // get request id.
        long id = Bytes.bytes2long(header, 4);
        if ((flag & FLAG_REQUEST) == 0) {
            // decode response.
            Response res = new Response(id);
            if ((flag & FLAG_EVENT) != 0) {
                res.setEvent(Response.HEARTBEAT_EVENT);
            }
            // get status.
            byte status = header[3];
            res.setStatus(status);
            if (status == Response.OK) {
                try {
                    Object data;                    if (res.isHeartbeat()) {
                        data = decodeHeartbeatData(channel, mockDeserialize(s, channel.getUrl(), is));
                    } else if (res.isEvent()) {
                        data = decodeEventData(channel, mockDeserialize(s, channel.getUrl(), is));
                    } else {
                        DecodeableRpcResult result;
                        if (channel.getUrl().getParameter(
                                Constants.DECODE_IN_IO_THREAD_KEY,
                                Constants.DEFAULT_DECODE_IN_IO_THREAD)) {
                            result = new DecodeableRpcResult(channel, res, is,
                                    (Invocation) getRequestData(id), proto);
                            result.decode();
                        } else {
                            result = new DecodeableRpcResult(channel, res,
                                    new UnsafeByteArrayInputStream(readMockMessageData(is)),
                                    (Invocation) getRequestData(id), proto);
                        }
                        data = result;
                    }
                    res.setResult(data);
                } catch (Throwable t) {
                    if (log.isWarnEnabled()) {
                        log.info("Decode response failed: " + t.getMessage(), t);
                    }
                    res.setStatus(Response.CLIENT_ERROR);
                    res.setErrorMessage(StringUtils.toString(t));
                }
            } else {
                res.setErrorMessage(mockDeserialize(s, channel.getUrl(), is).readUTF());
            }
            return res;
        } else {
            // decode request.
            Request req = new Request(id);
            req.setVersion("2.0.0");
            req.setTwoWay((flag & FLAG_TWOWAY) != 0);
            if ((flag & FLAG_EVENT) != 0) {
                req.setEvent(Request.HEARTBEAT_EVENT);
            }
            try {
                Object data;
                if (req.isHeartbeat()) {
                    data = decodeHeartbeatData(channel, mockDeserialize(s, channel.getUrl(), is));
                } else if (req.isEvent()) {
                    data = decodeEventData(channel, mockDeserialize(s, channel.getUrl(), is));
                } else {
//                    DecodeableRpcInvocation inv;
//                    if (channel.getUrl().getParameter(
//                            Constants.DECODE_IN_IO_THREAD_KEY,
//                            Constants.DEFAULT_DECODE_IN_IO_THREAD)) {
//                        inv = new DecodeableRpcInvocation(channel, req, is, proto);
//                        inv.decode();
//                    } else {
//                        inv = new DecodeableRpcInvocation(channel, req,
//                                new UnsafeByteArrayInputStream(readMessageData(is)), proto);
//                    }
                    MockDecodeableRpcInvocation inv;
                    if (channel.getUrl().getParameter(
                            Constants.DECODE_IN_IO_THREAD_KEY,
                            Constants.DEFAULT_DECODE_IN_IO_THREAD)) {
                        inv = new MockDecodeableRpcInvocation(channel, req, is, proto);
                        inv.decode();
                    } else {
                        inv = new MockDecodeableRpcInvocation(channel, req,
                                new UnsafeByteArrayInputStream(readMockMessageData(is)), proto);
                    }
                    data = inv;
                }
                req.setData(data);
            } catch (Throwable t) {
                if (log.isWarnEnabled()) {
                    log.warn("Decode request failed: " + t.getMessage(), t);
                }
                // bad request
                req.setBroken(true);
                req.setData(t);
            }
            return req;
        }
    }

    private ObjectInput mockDeserialize(Serialization serialization, URL url, InputStream is)
            throws IOException {
        return serialization.deserialize(url, is);
    }

    private byte[] readMockMessageData(InputStream is) throws IOException {
        int count = 0;
        if (is.available() > 0) {
            byte[] result = new byte[is.available()];
            while( (count = is.read(result))>0){
            }
            return result;
        }
        return new byte[]{};
    }
}
