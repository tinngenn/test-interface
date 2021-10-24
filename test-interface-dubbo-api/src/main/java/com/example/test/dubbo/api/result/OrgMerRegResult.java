package com.example.test.dubbo.api.result;

import com.example.test.dubbo.api.request.BestRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  企业商户注册页面返回的，页面表单数据对象。
 *
 * @author shenyuan
 * @version v0.1 2020/07/09 09:18
 */
@Getter
@Setter
@ToString
@ApiModel(value="OrgMerRegResult",description="企业商户注册接口返回对象")
public class OrgMerRegResult extends BestRequestDTO {
        /**
     * 序列号
     */
    private static final long serialVersionUID = -42182116365924L;
    /**
     *  登录号
     */
    @ApiModelProperty(value="loginNo",name="登录号",required=true)
    private String  loginNo;

    /**
     *  商户号
     */
    @ApiModelProperty(value="merchantCode",name="商户号",required=true)
    private String  merchantCode;

    /**
     *  商户名称
     */
    @ApiModelProperty(value="merchantName",name="商户名称",required=true)
    private String  merchantName;

    /**
     *  商户简称
     */
    @ApiModelProperty(value="merchantShortName",name="商户简称",required=true)
    private String  merchantShortName;

    /**
     *  手机号
     */
    @ApiModelProperty(value="contactPhone",name="手机号",required=true)
    private String  contactPhone;

    /**
     *  协议号
     */
    @ApiModelProperty(value="contractNo",name="协议号",required=true)
    private String  contractNo;

    /**
     *  签约解决方案
     */
    @ApiModelProperty(value="prodCode",name="产品码",required=true)
    private String  prodCode;

    /**
     *  操作员号
     */
    @ApiModelProperty(value="operatorNo",name="操作员号",required=true)
    private String  operatorNo;

    /**
     *  错误码
     */
    @ApiModelProperty(value="errorMessage",name="错误码")
    private String  errorMessage;

    /**
     *  平台登录用户名
     */
    @ApiModelProperty(value="rosefinchUsername",name="平台登录用户名")
    private String  rosefinchUsername;
}
