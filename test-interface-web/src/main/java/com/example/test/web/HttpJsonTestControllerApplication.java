package com.example.test.web;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.common.Constants;
import com.example.test.dubbo.cosurmer.OrgMerchantDubboCall;
import com.example.test.service.HttpTestService;
import com.example.test.utils.CotrollerFuction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Controller
@SpringBootApplication
@Api(tags = "Http 测试接口 Json")
public class HttpJsonTestControllerApplication {

    @Autowired
    CotrollerFuction cotrollerFuction;

    @Autowired
    HttpTestService httpTestService;


    @ApiOperation("Http返回Json  双引号  响应 Content-type:application/json;charset=UTF-8" )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "orderNo", value = "订单号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "payOrgCode", value = "支付机构号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "innerMercNo", value = "内部商户号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "signNo", value = "协议号", required = false , dataType = "Integer"),
                    @ApiImplicitParam(name = "amount", value = "金额", required = false , dataType = "Long"),
                    @ApiImplicitParam(name = "bankRespTime", value = "响应时间", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "status", value = "状态", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "bizStsCd", value = "结果编码", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "bizStsDesc", value = "结果详情", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "payRespNo", value = "响应流水号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "count", value = "节点循环次数", required = false , dataType = "int"),
                    @ApiImplicitParam(name = "traceLogId", value = "日志", required = false , dataType = "List<Object>"),
                    @ApiImplicitParam(name = "objList", value = "枚举值类", required = false , dataType = "String")
            })
    @ResponseBody
    @RequestMapping(value="/httpGetJson" ,
            method = {RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
    public String httpGetJson(Model model, HttpServletRequest httpRequest, HttpServletResponse response) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());

        //打印请求内容
        Map<String,String> parmMap = cotrollerFuction.printMessage(httpRequest);
        return  httpTestService.getDtoToJson(parmMap,SerializerFeature.WriteClassName,
                SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }



    @ApiOperation("Http返回Json  单引号 响应 Content-type:application/json;charset=UTF-8" )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "orderNo", value = "订单号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "payOrgCode", value = "支付机构号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "innerMercNo", value = "内部商户号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "signNo", value = "协议号", required = false , dataType = "Integer"),
                    @ApiImplicitParam(name = "amount", value = "金额", required = false , dataType = "Long"),
                    @ApiImplicitParam(name = "bankRespTime", value = "响应时间", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "status", value = "状态", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "bizStsCd", value = "结果编码", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "bizStsDesc", value = "结果详情", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "payRespNo", value = "响应流水号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "count", value = "节点循环次数", required = false , dataType = "int"),
                    @ApiImplicitParam(name = "traceLogId", value = "日志", required = false , dataType = "List<Object>"),
                    @ApiImplicitParam(name = "objList", value = "枚举值类", required = false , dataType = "String")
            })
    @ResponseBody
    @RequestMapping(value="/httpGetJson/single" ,
            method = {RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
    public String httpGetJson_single(Model model, HttpServletRequest httpRequest, HttpServletResponse response) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());

        //打印请求内容
        Map<String,String> parmMap = cotrollerFuction.printMessage(httpRequest);
        return  httpTestService.getDtoToJson(parmMap, SerializerFeature.WriteClassName,
                SerializerFeature.SortField,
                SerializerFeature.UseSingleQuotes,
                SerializerFeature.WriteNullStringAsEmpty);
    }



    @ApiOperation("Http返回Json  响应 Content-type:text/plain;charset=UTF-8" )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "orderNo", value = "订单号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "payOrgCode", value = "支付机构号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "innerMercNo", value = "内部商户号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "signNo", value = "协议号", required = false , dataType = "Integer"),
                    @ApiImplicitParam(name = "amount", value = "金额", required = false , dataType = "Long"),
                    @ApiImplicitParam(name = "bankRespTime", value = "响应时间", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "status", value = "状态", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "bizStsCd", value = "结果编码", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "bizStsDesc", value = "结果详情", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "payRespNo", value = "响应流水号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "count", value = "节点循环次数", required = false , dataType = "int"),
                    @ApiImplicitParam(name = "traceLogId", value = "日志", required = false , dataType = "List<Object>"),
                    @ApiImplicitParam(name = "objList", value = "枚举值类", required = false , dataType = "String")
            })
    @ResponseBody
    @RequestMapping(value="/httpGetJson/text/plain" ,
            method = {RequestMethod.GET,RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public String httpGetJson_Text(Model model, HttpServletRequest httpRequest, HttpServletResponse response) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());

        //打印请求内容
        Map<String,String>  parmMap = cotrollerFuction.printMessage(httpRequest);
        return  httpTestService.getDtoToJson(parmMap,SerializerFeature.WriteClassName,
                SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }


    @ApiOperation("Http返回Json  响应 Content-type:text/html;charset=UTF-8" )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "orderNo", value = "订单号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "payOrgCode", value = "支付机构号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "innerMercNo", value = "内部商户号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "signNo", value = "协议号", required = false , dataType = "Integer"),
                    @ApiImplicitParam(name = "amount", value = "金额", required = false , dataType = "Long"),
                    @ApiImplicitParam(name = "bankRespTime", value = "响应时间", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "status", value = "状态", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "bizStsCd", value = "结果编码", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "bizStsDesc", value = "结果详情", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "payRespNo", value = "响应流水号", required = false , dataType = "String"),
                    @ApiImplicitParam(name = "count", value = "节点循环次数", required = false , dataType = "int"),
                    @ApiImplicitParam(name = "traceLogId", value = "日志", required = false , dataType = "List<Object>"),
                    @ApiImplicitParam(name = "objList", value = "枚举值类", required = false , dataType = "String")
            })
    @ResponseBody
    @RequestMapping(value="/httpGetJson/text/html" ,
            method = {RequestMethod.GET,RequestMethod.POST}, produces="text/html;charset=UTF-8")
    public String httpGetJson_html(Model model, HttpServletRequest httpRequest, HttpServletResponse response) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());

        //打印请求内容
        Map<String,String>  parmMap = cotrollerFuction.printMessage(httpRequest);
        return  httpTestService.getDtoToJson(parmMap,SerializerFeature.WriteClassName,
                SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }



   @ApiOperation("Http返回Json  响应 Content-type:text/plain;charset=UTF-8" )
    @ResponseBody
    @RequestMapping(value="/httpGetJson/Test" ,
            method = {RequestMethod.GET,RequestMethod.POST})
    public String test(Model model, HttpServletRequest httpRequest, HttpServletResponse response) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());

        //打印请求内容
        Map<String,String>  parmMap = cotrollerFuction.printMessage(httpRequest);
        return  "{\"$type\":\"Result\",\"errorCode\":\"\",\"errorMsg\":\"\",\"primaryErrorCode\":\"\",\"primaryErrorIP\":\"\",\"primaryErrorMsg\":\"\",\"result\":{\"$type\":\"AhupDeductBO\",\"amount\":3333333333,\"bankRespTime\":\"4444444444\",\"bizStsCd\":\"5555555555\",\"bizStsDesc\":\"6666666666\",\"count\":8888888,\"id\":\"\",\"innerMercNo\":\"1111111111\",\"objList\":[\"aaaaaa\",\"bbbbbb\",111111,222222,true,false],\"orderNo\":\"20211007131331737\",\"payOrgCode\":\"ICBC\",\"payRespNo\":\"777777777\",\"signNo\":222222,\"status\":true,\"traceLogId\":\"4b2c3064-0e88-444e-8dd9-59490f6984ad\"},\"success\":true}";
    }



    @ApiOperation("Http_返回递归Json 响应 Content-type:application/json;charset=UTF-8")
    @RequestMapping(value="/httpGetJsonRecursion", method = {RequestMethod.GET,RequestMethod.POST},
            produces="application/json;charset=UTF-8")
    @ResponseBody
    public String httpGetJsonRecursion(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        cotrollerFuction.printMessage(httpRequest);
        return httpTestService.getDtoToJsonRecursion();
    }


    @ApiOperation("Http(Path)返回Json 响应 Content-type:application/json;charset=UTF-8")
    @ApiImplicitParams(
            {
                @ApiImplicitParam(name = "orderNo", value = "订单号", required = false , dataType = "String"),
                @ApiImplicitParam(name = "payOrgCode", value = "支付机构号", required = false , dataType = "String"),
                @ApiImplicitParam(name = "innerMercNo", value = "内部商户号", required = false , dataType = "String"),
                @ApiImplicitParam(name = "signNo", value = "协议号", required = false , dataType = "Integer"),
                @ApiImplicitParam(name = "amount", value = "金额", required = false , dataType = "Long"),
                @ApiImplicitParam(name = "bankRespTime", value = "响应时间", required = false , dataType = "String"),
                @ApiImplicitParam(name = "status", value = "状态", required = false , dataType = "String"),
                @ApiImplicitParam(name = "bizStsCd", value = "结果编码", required = false , dataType = "String"),
                @ApiImplicitParam(name = "bizStsDesc", value = "结果详情", required = false , dataType = "String"),
                @ApiImplicitParam(name = "payRespNo", value = "响应流水号", required = false , dataType = "String"),
                @ApiImplicitParam(name = "count", value = "节点循环次数", required = false , dataType = "int"),
                @ApiImplicitParam(name = "traceLogId", value = "日志", required = false , dataType = "List<Object>"),
                @ApiImplicitParam(name = "objList", value = "枚举值类", required = false , dataType = "String")
            })
    @ResponseBody
    @RequestMapping(value="/httpPathGetJson/{id}", method = {RequestMethod.GET,RequestMethod.POST},
            produces="application/json;charset=UTF-8")
    public String httpPathGetJson(@PathVariable String id, Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map parmMap =  cotrollerFuction.printMessage(httpRequest);
        return  httpTestService.getDtoToJson(parmMap,SerializerFeature.WriteClassName,
                SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }


}
