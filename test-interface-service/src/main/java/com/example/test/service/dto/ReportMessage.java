package com.example.test.service.dto;


import com.example.test.service.emum.ExeEventEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * xcenter kafka消息体
 *
 * @author sy
 * @version
 */
@Setter
@Getter
@ToString
public class ReportMessage<T> {

    private ClientInfo clientInfo;

    private T data;

    private Date reportTime;

    private ExeEventEnum event;

}