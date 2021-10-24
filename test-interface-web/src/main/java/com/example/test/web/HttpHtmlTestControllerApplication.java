package com.example.test.web;




import com.example.test.common.Constants;
import com.example.test.dubbo.api.dubboResult.Result;
import com.example.test.dubbo.api.result.OrgMerRegResult;
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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Slf4j
@Controller
@SpringBootApplication
@Api(tags = "Http 测试接口 Html")
public class HttpHtmlTestControllerApplication {


    @Autowired
    HttpTestService httpTestService;

    @Autowired
    CotrollerFuction cotrollerFuction;



    /**
     *  商户签约测试页面
     * */

    @ApiOperation("Http 测试接口 HTML 内嵌Css文件")
    @ApiImplicitParams(
            {
                @ApiImplicitParam(name = "loginNo", value = "登录名", required = false , dataType = "string"),
                @ApiImplicitParam(name = "merchantName", value = "商户名", required = false , dataType = "string"),
                @ApiImplicitParam(name = "merchantShortName", value = "商户简称", required = false , dataType = "string"),
                @ApiImplicitParam(name = "contactPhone", value = "手机号", required = false , dataType = "string"),
                @ApiImplicitParam(name = "prodCode", value = "产品码", required = false , dataType = "string"),
                @ApiImplicitParam(name = "rosefinchUsername", value = "平台用户名", required = false , dataType = "string"),
                @ApiImplicitParam(name = "count", value = "生成个数", required = false , dataType = "string")
            })
    @RequestMapping(value="/httpGetHtml/css" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="text/html;charset=UTF-8")
    public String httpGetHtml_css(Model model, HttpServletRequest httpRequest) {

        getHttpResultD(model, httpRequest);
        return "httpTestResultCss";

    }



    @ApiOperation("Http 测试接口 HTML 外挂Css文件")
    @ApiImplicitParams(
            {
               @ApiImplicitParam(name = "loginNo", value = "登录名", required = false , dataType = "string"),
               @ApiImplicitParam(name = "merchantName", value = "商户名", required = false , dataType = "string"),
               @ApiImplicitParam(name = "merchantShortName", value = "商户简称", required = false , dataType = "string"),
               @ApiImplicitParam(name = "contactPhone", value = "手机号", required = false , dataType = "string"),
               @ApiImplicitParam(name = "prodCode", value = "产品码", required = false , dataType = "string"),
               @ApiImplicitParam(name = "rosefinchUsername", value = "平台用户名", required = false , dataType = "string"),
               @ApiImplicitParam(name = "count", value = "生成个数", required = false , dataType = "string")
            })
    @RequestMapping(value="/httpGetHtml" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="text/html;charset=UTF-8")
    public String httpGetHtml(Model model, HttpServletRequest httpRequest) {
        cotrollerFuction.printMessage(httpRequest);
        //设置返回值
        getHttpResultD(model, httpRequest);
        return "httpTestResult";
    }

    private void getHttpResultD(Model model, HttpServletRequest httpRequest) {

        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parameterMap = cotrollerFuction.printMessage(httpRequest);
        Result<List<OrgMerRegResult>> httpTestResult = new Result<>();
        List<OrgMerRegResult> list = new ArrayList<>();
        OrgMerRegResult orgMerRegResult1 = new OrgMerRegResult();
        orgMerRegResult1.setLoginNo(parameterMap.get("loginNo"));
        orgMerRegResult1.setMerchantName(parameterMap.get("merchantName"));
        orgMerRegResult1.setMerchantShortName(parameterMap.get("merchantShortName"));
        orgMerRegResult1.setMerchantCode("317000000001");
        orgMerRegResult1.setContractNo("318709321381291");
        orgMerRegResult1.setContactPhone(parameterMap.get("contactPhone"));
        orgMerRegResult1.setOperatorNo("3179893283981");
        orgMerRegResult1.setProdCode(parameterMap.get("prodCode"));
        orgMerRegResult1.setMerchantCode(parameterMap.get("merchantCode"));
        orgMerRegResult1.setRosefinchUsername(parameterMap.get("rosefinchUsername"));
        orgMerRegResult1.setTraceLogId(MDC.get(Constants.TRACE_LOG_ID));
        OrgMerRegResult orgMerRegResult2 = new OrgMerRegResult();
        orgMerRegResult2.setLoginNo("18016079961");
        orgMerRegResult2.setMerchantCode("317000000002");
        orgMerRegResult2.setMerchantName("测试商户名2");
        orgMerRegResult2.setMerchantShortName("测试商户名简称2");
        orgMerRegResult2.setContractNo("318709321381292");
        orgMerRegResult2.setContactPhone("18016079961");
        orgMerRegResult2.setOperatorNo("3179893283982");
        orgMerRegResult2.setProdCode("80080002");
        orgMerRegResult2.setRosefinchUsername("lishengsen");
        orgMerRegResult2.setTraceLogId(MDC.get(Constants.TRACE_LOG_ID));
        list.add(orgMerRegResult1);
        list.add(orgMerRegResult2);
        httpTestResult.setResult(list);
        log.info("httpTestResult:{}" , httpTestResult.toString());
        model.addAttribute("httpTestResult", httpTestResult);

    }


    @ApiOperation("Http_返回Png")
    @RequestMapping(value="/httpGetPng", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String httpGetPng(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        cotrollerFuction.printMessage(httpRequest);
        return "<html>\n" +
                "<body>\n" +
                " \n" +
                "<h1>My first SVG</h1>\n" +
                " \n" +
                "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n" +
                "  <circle cx=\"100\" cy=\"50\" r=\"40\" stroke=\"black\"\n" +
                "  stroke-width=\"2\" fill=\"red\" />\n" +
                "</svg>\n" +
                " \n" +
                "</body>\n" +
                "</html>";
    }


    @ApiOperation("Http返回Html 响应 Content-type:application/xhtml+xml;charset=UTF-8")
    @RequestMapping(value="/httpGetHtml/application/xhtml/xml", method = {RequestMethod.GET,RequestMethod.POST},
            produces="application/xhtml+xml;charset=UTF-8")
    public void httpGetHtml_xhtml(Model model, HttpServletRequest httpRequest,HttpServletResponse response)
                                                                        throws IOException {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        cotrollerFuction.printMessage(httpRequest);
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(new AnnotationConfigApplicationContext());
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.XML);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        Context ctx = new Context();
        ctx.setVariable("notes", Arrays.asList("one note", "two note"));
        String xml = engine.process("xhtml", ctx);
        response.setHeader("Content-Disposition", "inline; filename=xhtml.html");
        response.setContentType("application/xhtml+xml");
        PrintWriter writer = response.getWriter();
        writer.print(xml);
        writer.close();
    }



    @ApiOperation("PUT 测试接口")
    @PutMapping(value="/Put",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String put(Model model, HttpServletRequest httpRequest) {
        cotrollerFuction.printMessage(httpRequest);
        return "PUT 请求返回";
    }

    @ApiOperation("DELETE 测试接口")
    @DeleteMapping(value = "/Delete")
    @ResponseBody
    public String Delete(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        cotrollerFuction.printMessage(httpRequest);
        return "DELETE 请求返回";
    }

    @ApiOperation("DELETE 测试接口")
    @PatchMapping(value = "/Patch")
    @ResponseBody
    public String Patch(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        cotrollerFuction.printMessage(httpRequest);
        return "PATCH 请求返回";
    }



    @ApiOperation("OPTIONS 测试接口")
    @RequestMapping(value = "/Options",method = {RequestMethod.OPTIONS})
    @ResponseBody
    public String Options(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        cotrollerFuction.printMessage(httpRequest);
        return "OPTIONS 请求返回";
    }




    @ApiOperation("Http 测试接口 HTML 响应 Content-type:application/xml;charset=UTF-8")
    @RequestMapping(value="/httpGetHtml/application/xml" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="application/xml;charset=UTF-8")
    public void httpGetHtml_test( HttpServletRequest httpRequest,HttpServletResponse response) throws IOException {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        cotrollerFuction.printMessage(httpRequest);
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(new AnnotationConfigApplicationContext());
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.XML);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        Context ctx = new Context();
        ctx.setVariable("notes", Arrays.asList("one note", "two note"));
        String xml = engine.process("test", ctx);
        response.setHeader("Content-Disposition", "inline; filename=test.html");
        response.setContentType("application/xml");
        PrintWriter writer = response.getWriter();
        writer.print(xml);
        writer.close();

    }


    @RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
    public String toIndex(){
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        return "index";
    }




    @RequestMapping(value = "/index/refresh", method = {RequestMethod.GET,RequestMethod.POST},
            produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String refresh(){
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        return  MDC.get(Constants.TRACE_LOG_ID) ;
    }




}
