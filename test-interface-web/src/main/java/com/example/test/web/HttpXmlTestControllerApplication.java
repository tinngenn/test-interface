package com.example.test.web;



import com.example.test.common.Constants;
import com.example.test.service.HttpTestService;
import com.example.test.utils.CotrollerFuction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.UUID;


@Slf4j
@Controller
@SpringBootApplication
@Api(tags = "Http 测试接口 Xml")
public class HttpXmlTestControllerApplication {


    @Autowired
    HttpTestService httpTestService;

    @Autowired
    CotrollerFuction cotrollerFuction;



    @ApiOperation("Http返回XML 响应 Content-type:application/xml;charset=UTF-8")
    @ApiImplicitParam(name = "count", value = "节点循环册次数", required = false , dataType = "String")
    @RequestMapping(value="/httpGetXml" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="application/xml;charset=UTF-8")
    @ResponseBody
    public String httpGetXml(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        return httpTestService.createXml(cotrollerFuction.printMessage(httpRequest));
    }


    @ApiOperation("Http返回XML 响应 Content-type:text/html;charset=UTF-8")
    @RequestMapping(value="/httpGetXml/text/html" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="text/html;charset=UTF-8")
    @ResponseBody
    public String httpGetXml_html(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        ;
        return httpTestService.createXml(cotrollerFuction.printMessage(httpRequest));
    }

    @ApiOperation("Http返回XML 响应 Content-type:text/plain;charset=UTF-8")
    @RequestMapping(value="/httpGetXml/text/plain" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String httpGetXml_Text(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());

        return httpTestService.createXml(cotrollerFuction.printMessage(httpRequest));
    }



    @ApiOperation("Http返回XML 请求仅支持 Content-type:application/xml;charset=UTF-8")
    @RequestMapping(value="/httpGetXml/onlyXml" , method = {RequestMethod.GET,RequestMethod.POST},
            consumes="application/xml",   produces="application/xml;charset=UTF-8")
    @ResponseBody
    public String httpGetXml_onlyXml(@RequestBody String xmlRequest, HttpServletRequest httpRequest) {
        try {
            MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
            log.info("xmlRequest:{}", xmlRequest);
            //Reader   reader   =   new   InputStreamReader(getClass().getResourceAsStream("xmlAndXsl.xml"));
            //log.info("reader:{}",reader.toString());


            return  httpTestService.createXml(   cotrollerFuction.printMessage(httpRequest));
        }catch (Exception e){
            log.info("Exception:{}",e.toString());
            return "系统运行错误";
        }
    }

    @ApiOperation("Http返回XML Xml链接Xsl  响应 Content-type:application/xml;charset=UTF-8")
    @RequestMapping(value="/httpGetXml/xmlAndXsl" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="application/xml;charset=UTF-8")
    public void xmlAndXsl(HttpServletResponse res) throws Exception {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(new AnnotationConfigApplicationContext());
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".xml");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.XML);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        Context ctx = new Context();
        ctx.setVariable("notes", Arrays.asList("one note", "two note"));
        String xml = engine.process("xmlAndXsl", ctx);
        res.setHeader("Content-Disposition", "inline; filename = xmlAndXsl.xml");
        res.setContentType("application/xml");
        PrintWriter writer = res.getWriter();
        writer.print(xml);
        writer.close();
    }


    @RequestMapping(value="/httpGetXml/xmlXsl" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="text/xml;charset=UTF-8")
    public void XmlXsl(HttpServletResponse res) throws Exception {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(new AnnotationConfigApplicationContext());
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".xml");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.XML);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        Context ctx = new Context();
        ctx.setVariable("notes", Arrays.asList("one note", "two note"));
        String xml = engine.process("xmlXsl", ctx);
        res.setHeader("Content-Disposition", "inline; filename=xmlXsl.xml");
        res.setContentType("text/xml");
        PrintWriter writer = res.getWriter();
        writer.print(xml);
        writer.close();
    }





   /* @ApiOperation("Http返回XML 包含XSL 请求仅支持 Content-type:application/xml;charset=UTF-8")
    @RequestMapping(value="/httpGetXml/xmlXsl" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="application/xml;charset=UTF-8")
    @ResponseBody
    public String httpGetXml_xmlXsl(HttpServletRequest httpRequest) {
        try {
            MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
            cotrollerFuction.printMessage(httpRequest);
            //log.info("xmlRequest:{}", xmlRequest);
            //Reader   reader   =   new   InputStreamReader(getClass().getResourceAsStream("xml.xml"));
            //log.info("reader:{}",reader.toString());
            Resource cXmlResource = new DefaultResourceLoader().getResource("classpath:/templates/xmlXsl.xml");
            Resource cXslResource = new DefaultResourceLoader().getResource("classpath:/static/test.xsl");


            String  cXmlFileStr = xmlFileToStr(cXmlResource.getInputStream());
            log.info("cXmlFileStr:{}",cXmlFileStr);
            // 将转换过的xml的String 样式打印到控制台
            String b = SaxTransform.xslTransform(cXmlFileStr, cXslResource);
            log.info(b);
            return  b;
        }catch (Exception e){
            log.info("Exception:{}",e.toString());
            return "系统运行错误";
        }
    }

    private String  xmlFileToStr(InputStream cXmlInputStream)throws Exception {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(cXmlInputStream);
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        log.info("writer:{}", writer.toString());
        return writer.toString();

    }*/
}
