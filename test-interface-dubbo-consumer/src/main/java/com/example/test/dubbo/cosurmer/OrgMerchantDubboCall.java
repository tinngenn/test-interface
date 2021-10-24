package com.example.test.dubbo.cosurmer;


import com.alibaba.dubbo.config.annotation.Reference;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.dubbo.api.DataFactoryMerchantService;
import com.example.test.dubbo.api.dubboResult.Result;
import com.example.test.dubbo.api.request.OrgMerchantRegisteDTO;
import com.example.test.dubbo.api.result.OrgMerRegResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 *  企业商户Dubbo调用方法集合
 *
 * @author shenyuan
 * @version v0.1 2020/08/09 12:18
 */
@Slf4j
@Component
public class OrgMerchantDubboCall {

    /**
     *  Dubbo 调用商服注册服务
     */
    @Reference(retries = 0, timeout = 5000, check = false )
    DataFactoryMerchantService dataFactoryMerchantService;



      /**
     * 调用商服注册服务，并给企业商户注册对象【OrgMerchantRegisterBO】赋返回值
     * @param   orgMerchantRegisteDTO    企业商户注册对象
     */

    public String orgMerRegisterCall(OrgMerchantRegisteDTO orgMerchantRegisteDTO){

        log.info("orgMerchantRegisteDTO--企业账户开户请求参数-->>:{}"
                 , orgMerchantRegisteDTO.toString());
        Result<List<OrgMerRegResult>> result;
        try {

            //避免并导致调用异常，循环尝试6次

                result = dataFactoryMerchantService.orgMerRegister(orgMerchantRegisteDTO);

                if(result.isSuccess()){
                    log.info("Dubbo调用返回：{}", result.toString());
                }
                else{
                    log.info(" 失败,错误码：{}", result.toString());
                }
            String str = JSONObject.toJSONString(result,
                    SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
            log.info("JSONJSONJSON:{}", str);
            return  str;
        }catch (Exception e){log.info("Dubbo调用异常：{}",e);}//捕获线程等待异常

        return  null;
    }

}
