package com.example.test.web;

import com.example.test.utils.CotrollerFuction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

@Slf4j
@Controller
@Api(tags = "Http文件上传测试接口")
public class FileDownloadControllerApplication {

    @Autowired
    CotrollerFuction cotrollerFuction;

    @ApiOperation("Http文件上传测试接口")
    @RequestMapping("/Download")
    public void Download(Model model, HttpServletRequest httpRequest, HttpServletResponse res) throws Exception {

        cotrollerFuction.printMessage(httpRequest);

        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(new AnnotationConfigApplicationContext());
        resolver.setPrefix("classpath:/static/");
        resolver.setSuffix(".gif");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.XML);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        Context ctx = new Context();
        ctx.setVariable("notes", Arrays.asList("one note", "two note"));
        String xml = engine.process("gif", ctx);
        res.setHeader("Content-Disposition", "attachment; filename=gif.gif");
        res.setContentType("application/xml");
        PrintWriter writer = res.getWriter();
        writer.print(xml);
        writer.close();
    }

    @RequestMapping(value = "/download/pdf", method = {RequestMethod.GET, RequestMethod.POST})
    public void download_pdf(HttpServletResponse response){
        try {
            Resource filePdf =
                    new DefaultResourceLoader().getResource("classpath:/static/files/pdf.pdf");

            //加载文件
            InputStream is = new BufferedInputStream(filePdf.getInputStream());
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            // 清空response
            response.reset();
            // 设置response的Header  inline   attachment
            response.addHeader("Content-Disposition", "attachment;filename=test.pdf");

           //+ new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            //response.addHeader("Content-Length", "" filePdf.l);
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/pdf");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();

        } catch (Exception e) {
           log.info(e.toString());
       }

    }
}