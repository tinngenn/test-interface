package com.example.test.web;

import com.example.test.common.Constants;
import com.example.test.service.mock.TestMockService;
import com.example.test.utils.CotrollerFuction;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * web服务类
 * @author shenyuan
 * @version v0.1 2020/07/06 16:17
 */

@Slf4j
@Controller
public class TestMockControllerApplication {


    @Autowired
    TestMockService testMockService;

    @Autowired
    CotrollerFuction cotrollerFuction;

    /**
     *  mock注册
     * */

    @RequestMapping(value="/testMockAdd" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String testMockAdd(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);

        testMockService.testMockAdd();
        return "添加mock";
    }

    /**
     *  删除注册
     * */

    @RequestMapping(value="/testMockDel" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String merchantSign(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);
        testMockService.testMockDel();

        return "删除mock";
    }
}
