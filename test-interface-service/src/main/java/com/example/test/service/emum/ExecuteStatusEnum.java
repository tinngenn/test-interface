package com.example.test.service.emum;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;


/**
 * xcenter 定时任务执行用例状态枚举
 *
 * @author sy
 * @version
 */
public enum ExecuteStatusEnum{
    WAIT(Integer.valueOf(1), "等待"),
    RUNNING(Integer.valueOf(2), "运行"),
    DONE(Integer.valueOf(3), "结束"),
    SKIP(Integer.valueOf(4), "跳过"),
    ERROR(Integer.valueOf(10), "错误"),
    TIMEOUT(Integer.valueOf(11), "超时"),
    CANCEL(Integer.valueOf(12), "取消"),
    EXIT(Integer.valueOf(13), "退出");

    ExecuteStatusEnum(Integer code, String desc) {

        this.code = code;
        this.desc = desc;
    }


    @JsonValue
    private final Integer code;

    private final String desc;

    private static final Map<Integer, ExecuteStatusEnum> MAPPING;

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    static {
        MAPPING = new HashMap<>((values()).length);
        for (ExecuteStatusEnum type : values())
            MAPPING.put(type.code, type);
    }

    @JsonCreator
    public static ExecuteStatusEnum getFromCode(Integer code) {
        return MAPPING.get(code);
    }
}