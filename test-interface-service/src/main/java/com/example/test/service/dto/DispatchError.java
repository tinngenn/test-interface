package com.example.test.service.dto;

import com.example.test.service.emum.DispatchErrorEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * xcenter 定时任错误枚举信息消息体
 *
 * @author sy
 * @version
 */
@Setter
@Getter
@ToString
public class DispatchError {

    private DispatchErrorEnum code;

    private String message;
}
