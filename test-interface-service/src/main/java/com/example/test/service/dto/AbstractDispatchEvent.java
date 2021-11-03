package com.example.test.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * xcenter 定时任务kafka消息体
 *
 * @author sy
 * @version
 */

@Setter
@Getter
@ToString
public class AbstractDispatchEvent {

    private String requestId;

    private String event;
}
