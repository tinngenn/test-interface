/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.example.test.dubbo.provider;



import com.alibaba.dubbo.config.annotation.Service;
import com.example.test.dubbo.api.DataFactoryMerchantService;
import com.example.test.dubbo.api.result.OrgMerRegResult;
import com.example.test.dubbo.api.dubboResult.Result;
import com.example.test.dubbo.api.request.OrgMerchantRegisteDTO;
import com.example.test.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 *  * 批量企业商户账户注册实现
 * <p>
 *     1、批量企业商户账户注册
 * </p>
 * @author shenyuan
 * @version Id: DataFactoryMerchantServiceImpl.java,
 */
@Slf4j
//@Component("providerService")
//@Service( timeout = 5000 , interfaceClass = DataFactoryMerchantService.class  )
public class DataFactoryMerchantServiceImpl implements DataFactoryMerchantService {


    /**
     * 1、企业账户注册服务
     *
     * @param orgMerchantRegisteDTO     量商户注册请求对象
     * @return                             批量商户注册处理返回
     */
    @Override
    public Result<List<OrgMerRegResult>> orgMerRegister(OrgMerchantRegisteDTO orgMerchantRegisteDTO) {

        MDC.put(Constants.TRACE_LOG_ID, orgMerchantRegisteDTO.getTraceLogId());
        //统计耗时用
        Long startMilliSeconds = System.currentTimeMillis();
        Result<List<OrgMerRegResult>> result = new Result<List<OrgMerRegResult>>();
        OrgMerRegResult orgMerRegResult = new OrgMerRegResult();
        orgMerRegResult.setLoginNo("11111");
        orgMerRegResult.setMerchantName("2222222");
        orgMerRegResult.setMerchantShortName("333333333333333");
        orgMerRegResult.setProdCode("44444444444444");
        orgMerRegResult.setRosefinchUsername("shenyuan");
        orgMerRegResult.setContactPhone("666666666666");
        List<OrgMerRegResult> list = new ArrayList<>();
        list.add(orgMerRegResult);
        result.setResult(list);
        log.info("dubbo服务端内部处理");
        result.setPrimaryErrorMsg("受理成功，异步执行后，看数据库结果！");

        return result;

    }
}