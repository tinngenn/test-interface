package com.example.test.dubbo.api.request;








import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;


import java.io.Serializable;

/**
 *  企业商户注册页面提交的，页面表单数据对象。
 *
 * @author shenyuan
 * @version v0.1 2020/07/09 09:18
 */
@Getter
@Setter
@ToString
public class BestRequestDTO  implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -42182116365624L;

    /**
     * 日志号
     */
    @ApiModelProperty(value="traceLogId",name="日志ID",required=true)
    @NotNull(message = "日志号不能为空")
    @NotBlank(message = "日志号不能为空")
    @Length(max = 32, message = "日志号不能超过32位")
    private String traceLogId;

    /**
     *  平台登录用户名
     */
    @ApiModelProperty(value="rosefinchUsername",name="平台登录名",required=true)
    @NotNull(message = "平台登录用户名不能为空")
    @NotBlank(message = "平台登录用户名不能为空")
    @Length(max = 16, message = "平台登录用户名长度不能超过16位")
    private String  rosefinchUsername;

}
