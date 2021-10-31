package com.example.test.dubbo.codec;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.utils.ReflectUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.exchange.Request;
import com.alibaba.dubbo.remoting.transport.CodecSupport;
import com.alibaba.dubbo.rpc.protocol.dubbo.DecodeableRpcInvocation;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;


@Slf4j
public class MockDecodeableRpcInvocation extends DecodeableRpcInvocation {

    private Channel channel;

    private byte serializationType;

    private InputStream inputStream;

    private Request request;

    private volatile boolean hasDecoded;

    public MockDecodeableRpcInvocation(Channel channel, Request request, InputStream is, byte id) {
        super(channel, request, is, id);
        this.channel = channel;
        this.request = request;
        this.inputStream = is;
        this.serializationType = id;
    }

    @Override
    public void decode() {
        if (!hasDecoded && channel != null && inputStream != null) {
            try {
                decode(channel, inputStream);
            } catch (Throwable e) {
                if (log.isWarnEnabled()) {
                    log.info("Decode rpc invocation failed: " + e.getMessage(), e);
                }
                request.setBroken(true);
                request.setData(e);
            } finally {
                hasDecoded = true;
            }
        }
    }

    @Override
    public Object decode(Channel channel, InputStream input) throws IOException {

        ObjectInput in = CodecSupport.getSerialization(channel.getUrl(), serializationType)
                .deserialize(channel.getUrl(), input);

        setAttachment(Constants.DUBBO_VERSION_KEY, in.readUTF());
        setAttachment(Constants.PATH_KEY, in.readUTF());
        setAttachment(Constants.VERSION_KEY, in.readUTF());

        setMethodName(in.readUTF());

        Object[] args;
        Class<?>[] pts;
        String desc = in.readUTF();
        if (desc.length() == 0) {
            pts = DubboCodec.EMPTY_CLASS_ARRAY;
            args = DubboCodec.EMPTY_OBJECT_ARRAY;
        } else {
            try {
                pts = ReflectUtils.desc2classArray(desc);
            } catch (ClassNotFoundException e) {
                log.info("dubbo请求中存在未引入的参数类型，将所有参数类型当作Object.class处理：" + desc);
                pts = desc2ObjectClassArray(desc);
            }
            args = new Object[pts.length];
            for (int i = 0; i < args.length; i++) {
                try {
                    args[i] = in.readObject(pts[i]);
                } catch (Exception e) {
                    if (log.isWarnEnabled()) {
                        log.info("Decode argument failed: " + e.getMessage(), e);
                    }
                }
            }
        }
        log.info("pts:{}",pts.toString());
        setParameterTypes(pts);

        try {
            Map<String, String> map = (Map<String, String>) in.readObject(Map.class);
            if (map != null && map.size() > 0) {
                Map<String, String> attachment = getAttachments();
                if (attachment == null) {
                    attachment = new HashMap<>();
                }
                attachment.putAll(map);
                setAttachments(attachment);
            }
        } catch (Exception e) {
            log.info("eeee:{}",e.toString());
            throw new IOException(StringUtils.toString("Read invocation data failed.", e));
        }

        //decode argument ,may be callback
//        for (int i = 0; i < args.length; i++) {
//            args[i] = MockCallbackServiceCodec.decodeInvocationArgument(channel, this, pts, i, args[i]);
//        }
        setArguments(args);

        return this;
    }

    private Class<?>[] desc2ObjectClassArray(String desc) {
        if (desc.length() == 0)
            return ReflectUtils.EMPTY_CLASS_ARRAY;

        List<Class<?>> cs = new ArrayList<Class<?>>();
        Matcher m = ReflectUtils.DESC_PATTERN.matcher(desc);
        while (m.find())
            cs.add(Object.class);
        return cs.toArray(ReflectUtils.EMPTY_CLASS_ARRAY);
    }

}
