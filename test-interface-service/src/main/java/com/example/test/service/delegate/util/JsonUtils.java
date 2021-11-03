package com.example.test.service.delegate.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;


@Slf4j
public final class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T jsonObjectToObj(JSONObject jsonObject, Class<T> clazz) {
        try {
            return (T)OBJECT_MAPPER.readValue(jsonObject.toString(), clazz);
        } catch (IOException e) {
            log.info("json格式化异常");
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonToObj(String jsonStr, Class<T> clazz) {
        try {
            return (T)OBJECT_MAPPER.readValue(jsonStr, clazz);
        } catch (IOException e) {
            log.info("json反序列化异常");
            throw new RuntimeException(e);
        }
    }

    public static JSONObject objToJson(Object o) {
        try {
            return new JSONObject(OBJECT_MAPPER.writeValueAsString(o));
        } catch (Exception e) {
            log.info("json序列化异常");
            throw new RuntimeException(e);
        }
    }

    public static String objToJsonString(Object o) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (Exception e) {
            log.info("json序列化异常");
            throw new RuntimeException(e);
        }
    }
}
