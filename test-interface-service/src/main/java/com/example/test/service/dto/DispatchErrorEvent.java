package com.example.test.service.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * xcenter 定时任错误消息体
 *
 * @author sy
 * @version
 */
@Setter
@Getter
@ToString
public class DispatchErrorEvent extends AbstractDispatchEvent{
    private DispatchError data;
}
