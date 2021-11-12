package com.example.test.service.dto;


import com.example.test.service.emum.ExecuteStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
public class MessageDoneEvent extends AbstractMessageEvent {
    @JsonIgnore
    private byte[] data;

    @JsonIgnore
    private String dataString;

    private String fileKey;

    private Integer bytesSize;

    private String fileName;

    private String contentType;

    @JsonIgnore
    private String fileRemark;

    private ExecuteStatusEnum status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.ARRAY)
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.ARRAY)
    private LocalDateTime endTime;

    @JsonIgnore
    private String name;


    private String id;

    private Integer stepCount;

    @JsonIgnore
    private Integer priority;

}
