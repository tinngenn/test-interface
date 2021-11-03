package com.example.test.service.emum;


import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;


/**
 * xcenter 定时任务执行错误枚举
 *
 * @author sy
 * @version
 */
public enum DispatchErrorEnum {

    COMMON(Integer.valueOf(1), "通用错误"),
    BUSY(Integer.valueOf(2), "繁忙"),
    ILLEGAL(Integer.valueOf(3), "参数非法/错误"),
    DUPLICATED(Integer.valueOf(4), "重复调用");

    DispatchErrorEnum(Integer code, String desc) {
    this.code = code;
    this.desc = desc;
    }

    @JsonValue
    private final Integer code;

    private final String desc;

    private static final Map<Integer, DispatchErrorEnum> MAPPING;

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    static {
        MAPPING = new HashMap<>((values()).length);
        for (DispatchErrorEnum type : values())
            MAPPING.put(type.code, type);
    }

    public static DispatchErrorEnum getFromCode(Integer code) {
        return MAPPING.get(code);
    }
    }