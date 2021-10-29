package runnableService;

import com.perfma.xcenter.facade.CommandRpcService;
import com.perfma.xcenter.facade.dto.CommandRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


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
