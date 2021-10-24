package com.example.test.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CotrollerFuction {
    public Map<String, String> printMessage(HttpServletRequest httpRequest){

        log.info("请示时间：{}",String.valueOf(Calendar.getInstance().getTimeInMillis()));

        //转换 http请求参数转换成Map对象
        Map<String, String> parameterMap = new HashMap<>();
        Enumeration<String> params = httpRequest.getParameterNames();
        String element ;
        while (params.hasMoreElements()) {
            element =   params.nextElement();

            log.info("name:{}  value:{}" ,element , httpRequest.getParameter(element));
            parameterMap.put(element , httpRequest.getParameter(element));
        }
        //请求方式

        log.info("getMethod：{}",httpRequest.getMethod());
        log.info("getRequestURI：{}",httpRequest.getRequestURI());
        log.info("getRequestURL：{}",httpRequest.getRequestURL());
        log.info("getRemoteHost:{}", httpRequest.getRemoteHost());
        log.info("getRemoteUser:{}", httpRequest.getRemoteUser());
        log.info("getProtocol:{}", httpRequest.getProtocol());
        log.info("getQueryString:{}", httpRequest.getQueryString());

        //Header部分
        log.info("Http文件头:{}", httpRequest.getHeaderNames());


        Enumeration<?> enum1 = httpRequest.getHeaderNames();
        while (enum1.hasMoreElements()) {
            String key = (String) enum1.nextElement();
            String value = httpRequest.getHeader(key);
            log.info("key:{} , value:{}",key,value);
        }

        return  parameterMap;
    }

    public Map<String, String> printMessage(HttpServletRequest httpRequest, RedirectAttributes attr){
        //转换 http请求参数转换成Map对象
        Map<String, String> parameterMap = new HashMap<>();
        Enumeration<String> params = httpRequest.getParameterNames();
        String element ;
        while (params.hasMoreElements()) {
            element =   params.nextElement();

            log.info("name:{}  value:{}" ,element , httpRequest.getParameter(element));
            attr.addAttribute(element,httpRequest.getParameter(element));
            parameterMap.put(element , httpRequest.getParameter(element));

        }
        //请求方式

        log.info("getMethod：{}",httpRequest.getMethod());
        log.info("getRequestURI：{}",httpRequest.getRequestURI());
        log.info("getRequestURL：{}",httpRequest.getRequestURL());
        log.info("getRemoteHost:{}", httpRequest.getRemoteHost());
        log.info("getRemoteUser:{}", httpRequest.getRemoteUser());
        log.info("getProtocol:{}", httpRequest.getProtocol());
        log.info("getQueryString:{}", httpRequest.getQueryString());

        //Header部分
        log.info("Http文件头:{}", httpRequest.getHeaderNames());

        Enumeration<?> enum1 = httpRequest.getHeaderNames();
        while (enum1.hasMoreElements()) {
            String key = (String) enum1.nextElement();
            String value = httpRequest.getHeader(key);
            log.info("key:{} , value:{}",key,value);
        }

        return  parameterMap;
    }

}
