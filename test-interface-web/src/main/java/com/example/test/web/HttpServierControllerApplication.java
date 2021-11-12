package com.example.test.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.common.Constants;
import com.example.test.dal.service.TestCaseDefineApiAutoService;
import com.example.test.dal.service.TestCaseDefineApiAutoStepService;
import com.example.test.dal.service.TestCaseService;
import com.example.test.dubbo.api.dubboResult.Result;
import com.example.test.dubbo.api.request.OrgMerchantRegisteDTO;
import com.example.test.dubbo.cosurmer.ClientRpcServiceCall;
import com.example.test.dubbo.cosurmer.OrgMerchantDubboCall;
import com.example.test.service.ApiOptionService;
import com.example.test.service.CopyTestCaseService;
import com.example.test.utils.CotrollerFuction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utils.ThreadPoolUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@SpringBootApplication
@Api(tags = "Http  工具方法接口  用于测试数据准备等")
public class HttpServierControllerApplication {


    @Autowired
    CotrollerFuction cotrollerFuction;

    @Autowired
    ApiOptionService apiOptionService;


    @Autowired
    CopyTestCaseService copyTestCaseService;


    @Autowired
    TestCaseService testCaseService;

    @Autowired
    TestCaseDefineApiAutoStepService testCaseDefineApiAutoStepService;

    @Autowired
    TestCaseDefineApiAutoService testCaseDefineApiAutoService;

    @Autowired
    OrgMerchantDubboCall orgMerchantDubboCall;

    @Autowired
    ClientRpcServiceCall clientRpcServiceCall;


    @ApiOperation("Http转换Dubbo测试接口 DataFactoryMerchantService.orgMerRmethod = {RequestMethod.GET,RequestMethod.POST}egister")
    @ResponseBody
    @RequestMapping(value="/httpConvertDubboTest" , method = {RequestMethod.GET,RequestMethod.POST},
            consumes="application/json", produces="application/json;charset=UTF-8")
    public String httpConvertDubboTest(@RequestBody String orgMerchantRequest,
                                       HttpServletRequest httpRequest) {
        cotrollerFuction.printMessage(httpRequest);
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        log.info("111:{}",orgMerchantRequest.toString());
/*        OrgMerchantRegisteDTO orgMerchantRegisteDTO = new OrgMerchantRegisteDTO();
        orgMerchantRegisteDTO.setLoginNo("18016078959");
        orgMerchantRegisteDTO.setRosefinchUsername("shenyuan");
        orgMerchantRegisteDTO.setTraceLogId("FFSD8FDF8H999");*/


        OrgMerchantRegisteDTO orgMerchantRegisteDTO
                = JSON.parseObject(orgMerchantRequest,OrgMerchantRegisteDTO.class);
        log.info(orgMerchantRegisteDTO.toString());
        return  orgMerchantDubboCall.orgMerRegisterCall(orgMerchantRegisteDTO);
    }




    @ResponseBody
    @RequestMapping(value="/getClientInfo" , method = {RequestMethod.GET,RequestMethod.POST},
            produces="application/json;charset=UTF-8")
    public String clientRpcServiceCall(
                                       HttpServletRequest httpRequest) {
        cotrollerFuction.printMessage(httpRequest);
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());


        return JSONObject.toJSONString(clientRpcServiceCall.getClientInfo(), SerializerFeature.WriteClassName,
                SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }



    @RequestMapping(value = "/createTestCase", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTestCase(Model model, HttpServletRequest httpRequest) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);
        log.info("parmMap:{}", parmMap.toString());

        Result result = new Result();
        try {
            long start = System.currentTimeMillis();
            //发送10次消息
            for (Integer i = 0; i < 100; i++) {
                try {
                    ApiOptionService apiOptionService = new ApiOptionService(parmMap);
                    apiOptionService.setInt(i);
                    ThreadPoolUtil.execute(apiOptionService);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.info(String.format("共计耗时{%s}毫秒", System.currentTimeMillis() - start));

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

       /* apiOptionService.createTestCase();
            apiOptionService.setParmMap(pramMap);
            result.setResult(new Object());*/

            result.setResult(new Object());

        } catch (Exception e) {
            log.info("创建测试用例异常：{}", e.toString());
            result.setErrorCode("A00000");
            result.setErrorMsg(e.toString());
        }

        model.addAttribute("result", result);
        return "createTestCaseResult";
    }


    @ApiOperation("Http 同一树下复制配置用例  根据TestCaseCode")
    @ApiImplicitParams(
            {
                @ApiImplicitParam(name = "testCaseCode", value = "用例ID", required = false , dataType = "string"),
                @ApiImplicitParam(name = "count", value = "复制数量", required = false , dataType = "string"),

            })
    @RequestMapping(value = "/copyTestCase/testCaseCode", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String copyTestCase(HttpServletRequest httpRequest) {

        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);


        if (!parmMap.containsKey("testCaseCode") && !StringUtils.isNotEmpty(parmMap.get("testCaseCode"))) {
            return "需要复制的testCaseCode 不能为空!";

        }
        if(testCaseService.getTestCase(parmMap.get("testCaseCode")).size()<1){
            return "请输入数据库存在的testCaseCode！";
        }
        if (parmMap.containsKey("count") && StringUtils.isNotEmpty(parmMap.get("count"))) {
            Integer count = Integer.valueOf(parmMap.get("count"));
                try {
                    long start = System.currentTimeMillis();
                    //发送10次消息
                    for (Integer i = 0; i < count; i++) {
                        try {
                            CopyTestCaseService copyTestCaseService = new CopyTestCaseService();
                            copyTestCaseService.setTestCaseCode(parmMap.get("testCaseCode"),i);
                            copyTestCaseService.setDbService(testCaseService,testCaseDefineApiAutoStepService,
                                    testCaseDefineApiAutoService);
                            ThreadPoolUtil.execute(copyTestCaseService);
                           // Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    log.info(String.format("共计耗时{%s}毫秒", System.currentTimeMillis() - start));

                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                } catch (Exception e) {
                    log.info("创建用例异常！", e);
                    return "请检查输入需要创建的个数！";

                }

        }
        return "执行完成，请去数据库或者配置页面确认！";
    }

}
