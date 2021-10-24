/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.example.test.dubbo.api;

import com.example.test.dubbo.api.result.OrgMerRegResult;
import com.example.test.dubbo.api.dubboResult.Result;
import com.example.test.dubbo.api.request.OrgMerchantRegisteDTO;
import io.swagger.annotations.*;

import java.util.List;

/**
 * <p>
 *     1.性能环境批量商户注册服务
 * </p>
 * @api     性能环境批量商户注册服务
 * @desc    商户注册
 * @author  shenyuan
 * @version Id: DataFactoryMerchantService.java, v 0.1 2020/10/30 13:59
 */

@Api(tags = "Dubbo测试接口类")
public interface DataFactoryMerchantService {

    /**
     * 1、企业商户注册
     *
     * @param orgMerchantRegisteDTO   批量企业商户注册请求对象
     * @return                      提现结果
     */
    @ApiOperation("Dubbo商户注册测试接口方法")
    Result<List<OrgMerRegResult>> orgMerRegister(
            @ApiParam(name="企业商户注册对象",value="企业商户注册对象OrgMerchantRegisteDTO",required=true)
                    OrgMerchantRegisteDTO orgMerchantRegisteDTO);
}