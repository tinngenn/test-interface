package com.example.test.service.dto;


import com.example.test.service.emum.ExecuteStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;


/**
 * xcenter 定时任务完成消息体
 *
 * @author sy
 * @version
 */

@Setter
@Getter
@ToString
public class DispatchDoneEvent extends AbstractDispatchEvent  {

    private List<ExecuteStatusEnum> data;
}
