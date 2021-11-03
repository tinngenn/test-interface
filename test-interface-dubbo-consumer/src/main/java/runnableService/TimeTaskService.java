package runnableService;

import com.perfma.xcenter.facade.CommandRpcService;
import com.perfma.xcenter.facade.dto.CommandRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



/**
 * 调用xcenter命令下发接口
 *
 * @author zhangzxiang91@gmail.com
 * @date 2021/06/24.
 */

@Slf4j
@Component
public class TimeTaskService implements Runnable  {

    private CommandRpcService commandRpcService;
    private CommandRequest     commandRequest;

    public TimeTaskService(CommandRpcService commandRpcService,CommandRequest commandRequest){
        this.commandRpcService = commandRpcService;
        this.commandRequest = commandRequest;
    }
    @Override
    public void run() {
        commandRpcService.invoke(commandRequest);
    }
}
