package com.example.test.service.dto;


import com.example.test.service.emum.ExecuteStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageDoneEventDTO {

    private  String requestId;
    private  String testCaseId;
    private  String stepCount;
    private  Integer executeId;
    private  String priority;
    private  String fileKey;
    private String startTime;
    private String endTime;
    private String fileNmae;
    private  String testCaseName;
    private ExecuteStatusEnum executeStatusEnum;

}
