package com.example.test.dubbo.serialization;

import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.hessian2.Hessian2SerializerFactory;

import java.io.IOException;
import java.io.OutputStream;


public class MockHessian2ObjectOutput implements ObjectOutput {

    private final MockHessian2Output mockHessian2Output;

    public MockHessian2ObjectOutput(OutputStream os) {
        mockHessian2Output = new MockHessian2Output(os);
        mockHessian2Output.setSerializerFactory(Hessian2SerializerFactory.SERIALIZER_FACTORY);
    }


    public void writeBool(boolean v) throws IOException {
        mockHessian2Output.writeBoolean(v);
    }

    public void writeByte(byte v) throws IOException {
        mockHessian2Output.writeInt(v);
    }

    public void writeShort(short v) throws IOException {
        mockHessian2Output.writeInt(v);
    }

    public void writeInt(int v) throws IOException {
        mockHessian2Output.writeInt(v);
    }

    public void writeLong(long v) throws IOException {
        mockHessian2Output.writeLong(v);
    }

    public void writeFloat(float v) throws IOException {
        mockHessian2Output.writeDouble(v);
    }

    public void writeDouble(double v) throws IOException {
        mockHessian2Output.writeDouble(v);
    }

    public void writeBytes(byte[] b) throws IOException {
        mockHessian2Output.writeBytes(b);
    }

    public void writeBytes(byte[] b, int off, int len) throws IOException {
        mockHessian2Output.writeBytes(b, off, len);
    }

    public void writeUTF(String v) throws IOException {
        mockHessian2Output.writeString(v);
    }

    public void writeObject(Object obj) throws IOException {
        mockHessian2Output.writeObject(obj);
    }

    public void flushBuffer() throws IOException {
        mockHessian2Output.flushBuffer();
    }
}
