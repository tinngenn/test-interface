package com.example.test.service.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public  class AbstractMessageEvent {

    private String requestId;

    private String event;

    private Integer executeId;
}
