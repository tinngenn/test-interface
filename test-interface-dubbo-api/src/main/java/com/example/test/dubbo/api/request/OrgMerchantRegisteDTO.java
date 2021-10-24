package com.example.test.dubbo.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 *  企业商户注册页面提交的，页面表单数据对象。
 *
 * @author shenyuan
 * @version v0.1 2020/07/09 09:18
 */
@Getter
@Setter
@ToString
@ApiModel(value="OrgMerchantRegisteDTO",description="企业商户注册接口请求对象")
public class OrgMerchantRegisteDTO extends  BestRequestDTO {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -42182116365324L;

    /**
     * 登录号
     */
    @ApiModelProperty(value="loginNo",name="登录号")
    @Length(max = 32, message = "登录号长度不能超过32位")
    private String loginNo;

    /**
     * 商户名称
     */
    @ApiModelProperty(value="merchantName",name="商户名称")
    @Length(max = 128, message = "商户名称长度不能超过128位")
    private String merchantName;

    /**
     * 商户简称
     */
    @ApiModelProperty(value="merchantShortName",name="商户简称")
    @Length(max = 128, message = "商户简称长度不能超过128位")
    private String merchantShortName;

    /**
     * 手机号
     */
    @ApiModelProperty(value="contactPhone",name="手机号")
    @Length(max = 11, message = "手机号长度不能超过11位")
    private String contactPhone;

    /**
     * 解决方案编号
     */
    @ApiModelProperty(value="prodCode",name="产品码")
    @Length(max = 32, message = "解决方案编号长度不能超过32位")
    private String prodCode;

    /**
     * 商户注册个数
     */
    @ApiModelProperty(value="count",name="生成个数")
    @NotNull(message = "商户注册个数不能为空")
    @NotBlank(message = "商户注册个数不能为空")
    private String count;


}
