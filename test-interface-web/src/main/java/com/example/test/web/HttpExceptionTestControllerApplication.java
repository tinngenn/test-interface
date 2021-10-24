package com.example.test.web;

import com.example.test.utils.CotrollerFuction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Map;

@Slf4j
@Controller
@SpringBootApplication
@Api(tags = "Http 自定义 测试接口")
public class HttpExceptionTestControllerApplication {


    @Autowired
    CotrollerFuction cotrollerFuction;


    @ApiOperation("Http 自定义 状态码和状态描述 默认200;自定义消息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "error", value = "状态码", required = false , dataType = "string"),
                    @ApiImplicitParam(name = "message", value = "状态描述", required = false , dataType = "string"),
            })
    @RequestMapping(value="/exception", method = {RequestMethod.GET,RequestMethod.POST})
    public void exception(HttpServletRequest httpRequest,HttpServletResponse response) throws IOException {
        String error = "200";
        String message = "自定义消息";
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        if(StringUtils.isNotEmpty(map.get("error"))){
            error = map.get("error");}

        if(StringUtils.isNotEmpty(map.get("message"))){
            message = map.get("message");}

       response.setStatus(Integer.valueOf(error));
       response.getWriter().append(message);
    }


    @ApiOperation("Http 自定义 延时时间测试接口 默认1000毫秒")
    @ApiImplicitParams(
            {
               @ApiImplicitParam(name = "timout", value = "超时时间", required = false , dataType = "string"),
            })
    @RequestMapping(value="/timeout", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String timeout(HttpServletRequest httpRequest) throws Exception {
        String timeout = "1000";

        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        if(map.containsKey("timeout") && StringUtils.isNotEmpty(map.get("timeout"))){
            timeout = map.get("timeout");}
            long start = System.currentTimeMillis();
            log.info("超时睡眠睡觉开始"+ start);
            Thread.sleep(Long.valueOf(timeout));
            long takeUpTime = System.currentTimeMillis() - start;
            log.info(String.format("共计耗时{%s}毫秒", takeUpTime));
        return  "响应超时设定时间：" + timeout  + "耗时：" + takeUpTime;
    }




}
