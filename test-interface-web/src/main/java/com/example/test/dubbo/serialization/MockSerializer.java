package com.example.test.dubbo.serialization;

import com.alibaba.com.caucho.hessian.io.AbstractHessianOutput;
import com.alibaba.com.caucho.hessian.io.MapSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MockSerializer extends MapSerializer {

    @Override
    public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {
        if (out.addRef(obj))
            return;

        Map map = (Map) obj;

        Class cl = obj.getClass();

        if (cl.equals(HashMap.class)
                || !super.getSendJavaType()
                || !(obj instanceof java.io.Serializable)) {
            out.writeMapBegin(null);
        } else {
            log.info("2222222222222222222222222");
            String genericType = getMockGenericTypeMap().get(map.hashCode());
            //out.writeMapBegin(genericType != null ? genericType : obj.getClass().getName());
            out.writeMapBegin("com.bestpay.bank.front.model.res.BankFrontPayRespDTO");
        }

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (entry.getKey() instanceof String) {
                try {
                    out.writeObject(resolveGenericType((String) entry.getKey(), entry));
                } catch (Exception e) {
                    throw new IOException("MockSerializer处理泛型出错", e);
                }
            } else {
                out.writeObject(entry.getKey());
            }
            out.writeObject(entry.getValue());
        }
        out.writeMapEnd();
    }

    private String resolveGenericType(String key, Map.Entry entry) throws Exception {
        if (key.contains("<") && key.contains(">") && key.contains(".")) {
            String genericType = key.substring(key.indexOf("<") + 1, key.indexOf(">"));
            if (entry.getValue() instanceof Map) {
                if (genericType.contains(",")) {
                    String[] gtArr = genericType.split("[,]");
                    Set<Map.Entry> entrySet = ((Map) entry.getValue()).entrySet();
                    for (Map.Entry internalEntry : entrySet) {
                        getMockGenericTypeMap().put(internalEntry.getKey().hashCode(), gtArr[0]);
                        getMockGenericTypeMap().put(internalEntry.getValue().hashCode(), gtArr[1]);
                    }
                } else {
                    getMockGenericTypeMap().put(entry.getValue().hashCode(), genericType);
                }
                key = key.substring(0, key.indexOf("<"));
            } else if (entry.getValue() instanceof Collection) {
                for (Object value : (Collection) entry.getValue()) {
                    getMockGenericTypeMap().put(value.hashCode(), genericType);
                }
                key = key.substring(0, key.indexOf("<"));
            }
        }
        return key;
    }
    private static final Map<Integer, String> mockGenericTypeMap = new ConcurrentHashMap<>();
    public static Map<Integer, String> getMockGenericTypeMap() {
        return mockGenericTypeMap;
    }
}
