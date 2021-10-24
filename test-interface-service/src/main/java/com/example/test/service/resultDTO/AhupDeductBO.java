package com.example.test.service.resultDTO;




import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 安徽银联代扣状态异步回调对象
 *
 * @author cx
 * @version Id: AhupDeductBO.java, v 0.1 2019/08/19 08:17 cx Exp $$
 */
@Getter
@Setter
@ToString
public class AhupDeductBO implements Serializable{


    /**
     * 支付请求流水号
     */
    private String id;



    /**
     * 支付请求流水号
     */
    private String orderNo;

    /**
     * 支付机构代码
     */
    private String payOrgCode;

    /**
     * 内部商户号
     */
    private String innerMercNo;

    /**
     * 协议号
     */
    private Integer signNo;

    /**
     *  金额
     */
    private Long amount;


    /**
     * 银行响应时间
     */
    private String bankRespTime;
    /**
     * 状态 (SUCCESS,FAIL,UNKNOWN)
     */
    private Boolean status;

    /**
     * 结果编码 (状态为未知或者失败时)
     */
    private String bizStsCd;

    /**
     * 结果详情 (状态为未知或者失败时)
     */
    private String bizStsDesc;

    /**
     * 响应流水号
     */
    private String payRespNo;

    /**
     * 计数
     */
    private int count;


    /**
     * 日志号
     */
    private String traceLogId;
    /**
     * 枚举值
     */
    private List<Object> objList;



    /**
     * 字符串数组
     */
    private String[] strArry={"aaaaaa","bbbbbb","cccccc","dddddd","eeeeee","ffffff"};


    /**
     * 数字串数组
     */
    private Integer[] intArry = {111111,222222,333333,444444,555555,666666};


    /**
     * 布尔串数组
     */
    private Boolean[] booleanArry = {true,true,true,false,false,false};


    /**
     * 长城key
     */
    private String longgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggText;


    /**
     * 长城key
     */
    private String nullTest;
}
