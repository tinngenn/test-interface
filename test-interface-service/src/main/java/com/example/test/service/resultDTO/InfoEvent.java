package com.example.test.service.resultDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString

@ApiModel("执行机引擎信息")
public class InfoEvent {

    @ApiModelProperty("引擎版本")
    private String version;

    @ApiModelProperty("引擎 git commit id")
    private String commit;

    @ApiModelProperty("node 版本")
    private String node;


    @ApiModelProperty("platform")
    private String platform;

    @ApiModelProperty("v8 版本")
    private String v8;

    @ApiModelProperty("openssl 版本")
    private String openssl;

    @ApiModelProperty("启动的用户名")
    private String username;

    @ApiModelProperty("进程ID")
    private String pid;

    @ApiModelProperty("启动的用户ID")
    private String uid;

    @ApiModelProperty("cpu 核心数")
    private String cpus;

    @ApiModelProperty("最大内存")
    private String totalmem;

    @ApiModelProperty("空闲内存")
    private String freemem;

    @ApiModelProperty("最大异步执行单元")
    private String maxWorker;

    @ApiModelProperty("当前空闲单元")
    private String idleWorker;

    @ApiModelProperty("是否繁忙")
    private String isBusy;

    @ApiModelProperty("引擎启动时间")
    private String startTime;

    @ApiModelProperty("用户tmp目录")
    private String tmpdir;
}
