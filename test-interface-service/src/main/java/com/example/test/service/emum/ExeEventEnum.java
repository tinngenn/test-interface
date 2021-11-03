package com.example.test.service.emum;


/**
 * xcenter 定时任务事件枚举
 *
 * @author sy
 * @version
 */
public enum ExeEventEnum {
    dispatch("dispatch", "调度"),
    info("info", "信息"),
    message("message", "事件"),
    query("query", "查询");

    ExeEventEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final String code;

    private final String desc;

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

}