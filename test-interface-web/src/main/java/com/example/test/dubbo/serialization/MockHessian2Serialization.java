package com.example.test.dubbo.serialization;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.alibaba.dubbo.common.serialize.hessian2.Hessian2ObjectInput;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class MockHessian2Serialization implements Serialization {

    public static final byte ID = 2;


    @Override
    public byte getContentTypeId() {
        return ID;
    }

    @Override
    public String getContentType() {
        return "x-application/hessian2";
    }

    @Override
    public ObjectOutput serialize(URL url, OutputStream out) {
        return new MockHessian2ObjectOutput(out);
    }

    @Override
    public ObjectInput deserialize(URL url, InputStream input) throws IOException {
        return new Hessian2ObjectInput(input);
    }

}
