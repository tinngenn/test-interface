package testNg;

import com.example.test.common.DTO.cell.Env;
import com.example.test.common.DTO.cell.Execute;
import testNg.enums.ParmEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SecondCompose {

    ThirdCompose thirdCompose = new ThirdCompose();

    public Env getEnv(){
        Env env = new Env();
        env.setVariable(thirdCompose.getVariable(ParmEnum.Hello));
        return  env;
    }





    public List<Execute> getExecutes(){

        List<Execute>  executes  = new ArrayList<>();

       executes.add(getExecute());

        return executes;
    }


    private Execute getExecute(){

        Execute  execute  = new Execute();
        execute.setEvents(11);
        execute.setMode(1);
        execute.setVariable(thirdCompose.getVariable(ParmEnum.Hello));
        execute.setSteps(thirdCompose.getSteps());
        return execute;

    }

}
