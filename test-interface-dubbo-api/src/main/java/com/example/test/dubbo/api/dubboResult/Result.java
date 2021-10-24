package com.example.test.dubbo.api.dubboResult;

//
// Source code recreated from a .class files by IntelliJ IDEA
// (powered by Fernflower decompiler)
//





import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="Result",description="Dubbo返回Result对象")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 8350327877975282483L;
    @ApiModelProperty(value="success",name="是否成功",required=true)
    private boolean success;
    @ApiModelProperty(value="result",name="返回对像")
    private T result;
    @ApiModelProperty(value="errorCode",name="错误码")
    private String errorCode;
    @ApiModelProperty(value="errorMsg",name="错误描述")
    private String errorMsg;
    @ApiModelProperty(value="primaryErrorCode",name="元错误码")
    private String primaryErrorCode;
    @ApiModelProperty(value="primaryErrorMsg",name="元错误描述")
    private String primaryErrorMsg;
    @ApiModelProperty(value="primaryErrorIP",name="元IP")
    private String primaryErrorIP;

    public Result() {
    }

    public Result(T result) {
        this.success = true;
        this.result = result;
    }

    public Result(String errorCode, String errorMsg) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Result(String errorCode, String errorMsg, String primaryErrorCode, String primaryErrorMsg, String primaryErrorIP) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.primaryErrorCode = primaryErrorCode;
        this.primaryErrorMsg = primaryErrorMsg;
        this.primaryErrorIP = primaryErrorIP;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getPrimaryErrorCode() {
        return this.primaryErrorCode;
    }

    public void setPrimaryErrorCode(String primaryErrorCode) {
        this.primaryErrorCode = primaryErrorCode;
    }

    public String getPrimaryErrorMsg() {
        return this.primaryErrorMsg;
    }

    public void setPrimaryErrorMsg(String primaryErrorMsg) {
        this.primaryErrorMsg = primaryErrorMsg;
    }

    public String getPrimaryErrorIP() {
        return this.primaryErrorIP;
    }

    public void setPrimaryErrorIP(String primaryErrorIP) {
        this.primaryErrorIP = primaryErrorIP;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("success", this.success)
                .add("result", this.result).add("errorCode", this.errorCode)
                .add("errorMsg", this.errorMsg).add("primaryErrorCode", this.primaryErrorCode)
                .add("primaryErrorMsg", this.primaryErrorMsg).add("primaryErrorIP", this.primaryErrorIP)
                .omitNullValues().toString();
    }
}
