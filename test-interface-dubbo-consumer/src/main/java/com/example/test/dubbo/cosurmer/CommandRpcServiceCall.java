package com.example.test.dubbo.cosurmer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.perfma.xcenter.facade.CommandRpcService;
import com.perfma.xcenter.facade.dto.CommandRequest;
import com.perfma.xcenter.facade.dto.CommandResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 *  调用Xcenter
 *
 * @author shenyuan
 * @version v0.1 2020/08/09 12:18
 */
@Slf4j
@Component
public class CommandRpcServiceCall {

    /**
     *  Dubbo 调用商服注册服务
     */
    @Reference(retries = 0, timeout = 5000, check = false )
    CommandRpcService commandRpcService;

    public CompletableFuture<Map<String, CommandResult>>   invoke(){
        CommandRequest commandRequest = new CommandRequest();
        Set<String> clientIds = new HashSet<>();
        clientIds.add("10.10.220.36@18492-7f3757cd");
        commandRequest.setClientIds(clientIds);
        commandRequest.setModule("TIMED_TASK");
        commandRequest.setCommand("DISPATCH");
   /*     commandRequest.setParams("8D57InJlcXVlc3RJZCI6ImFwaUF1dG9fdGltZV9hNjI2N2I4Yy1mNmI0LTQ5OWItODE2OC0zOTlhODkxMWEzNGMiLCJkYXRhIjp7ImVudgcA9R1uYW1lIjoi5rWL6K+VMiIsImh0dHBTZXJ2ZXIiOltdfSwiZXhlY3V0ZSI6Wy4AAHAAkG91dF80IiwiaYkA8QgxMjE3MjEiLCJtb2RlIjoxLCJzdGVwczQAQEB0eXA1ABQxLQDyGDk5NTZlODg4LTExYmYtNDZlZi04Yjc5LTI5NTEwMjUwM2MxNSIsIjcA8A4xLCJkaXNhYmxlZCI6ZmFsc2UsInByZVNjcmlwdKIAaCwicG9zdBAAk2Fzc2lnbm1lbhAAUG1ldGhveQC2UE9TVCIsInBhdGjFAAARAJFyb3RvY29sIjr+AAASAPMAb3J0Ijo4MDkyLCJob3N0+ADAMTAuMTAuMjIwLjM2zgBQelVzZUVIAQOZAHNoZWFkZXJzlwBCYXJhbQMBOWtleXEAQXZhbHVQAHUyMDAwMCJ9LgBmU3RyaW5nLAATOiIAYiwiZm9ybVgAAAoAEUTFAQJmAGJ0aEluZm8OAGBjb25maWfYAfUCbWF4UmVkaXJlY3RzIjoxMCx/ACA6N1MAEDBJAERsbG93JgBkIjp0cnVlLgFhUmV3cml0jAACFwAAGQEVMukA8AFyZWplY3RVbmF1dGhvcml6nQECKABRcmV0cnm3AdN1c2VKU09OU2NoZW1hOgARfZ4BIWVymgFNfV19LFMCGTJTAh80UwL//zMZM1MCHzVTAv//MxkxUwIfNlMC//8zGTVTAh83UwL//zMZNlMCHzhTAv//Mxk3UwIfOVMC//8zGDhTAi8zMFMC//8zGTlTAg+YEv//NCkxMFQCHzJUAv//NAlHEC8zM1QC//80CUEXHzNBF///NBkxQhcfM0IX//81CTweHzNDF///NBkxRBcfM0QX//80GTFFFx8zRRf//zQZMUYXHzNGF///NBkxRxcfNEcX//80GTFIFx804Cn//zUJSBcfNEgX//80GTJIFx80SBf//zQZMkgXHzRIF///NBkySBcfNEgX//80GTJIFx80SBf//xnAcnQiOltdfV19XX19");
        commandRequest.setReturn(true);*/
        commandRequest.setParams("8D57InJlcXVlc3RJZCI6ImFwaUF1dG9fdGltZV84YjNiNzY0Ny1jZmRhLTQ5M2ItOTUzOS03MmI4NzIzZTQwMjEiLCJkYXRhIjp7ImVudgcA9R1uYW1lIjoi5rWL6K+VMiIsImh0dHBTZXJ2ZXIiOltdfSwiZXhlY3V0ZSI6Wy4AACQAsEdldEh0bWwiLCJpiwDxCDEyMTc0OCIsIm1vZGUiOjEsInN0ZXBzNgBAQHR5cDcAFDEtAPIYYjMzM2ViMjYtMGMwMy00NjE3LTlkZTItN2YyNWQ2Y2RiZTMwIiwiNwDwDjEsImRpc2FibGVkIjpmYWxzZSwicHJlU2NyaXB0pABoLCJwb3N0EACTYXNzaWdubWVuEABQbWV0aG95APoAUE9TVCIsInBhdGgiOiIvyACDcHJvdG9jb2zhAAASAPMAb3J0Ijo4MDkyLCJob3N0/wDAMTAuMTAuMjIwLjM20wBQelVzZUVPAQOeAHNoZWFkZXJznABJYXJhbQwA8gFTdHJpbmciOiIiLCJmb3JtHAAACgARRJwBAioAYnRoSW5mbw4AYGNvbmZpZ68B8ANtYXhSZWRpcmVjdHMiOjEwLCL8AZBvdXQiOjUwMDBHAERsbG93JABkIjp0cnVlAQFhUmV3cml0fAACFwAA5wAVMrcA8AFyZWplY3RVbmF1dGhvcml6cAECKABRcmV0cnmKAdN1c2VKU09OU2NoZW1hOgARfXEBIWVybQEQfb0A4HJpb3JpdHkiOjN9XX19");
        commandRequest.setReturnTimeout(140000l);
        return  commandRpcService.invoke(commandRequest);
    }

}
