package testNg;

import testNg.enums.ParmEnum;
import com.example.test.common.DTO.cell.Steps;
import com.example.test.common.DTO.cell.Variable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ThirdCompose {

    FourthCompose fourthCompose = new FourthCompose();

    public Variable getVariable(ParmEnum keyNsme){

        Variable variable = new Variable();

        switch (keyNsme) {
            case Key:
                variable.setKey(fourthCompose.getKey());
                break;
            default:

        }

        return  variable;
    }

    public List<Steps> getSteps(){
        List<Steps> Steps = new ArrayList<>();

        Steps.add(getStep());
        return  Steps;
    }



    private Steps getStep(){
        Steps step = new Steps();
        step.setType(1);
        step.setMethod("POST");
        step.setHostname("10.10.220.36");
        step.setPath("/timeout");
        step.setProtocol("http");
        step.setPort(8092);
        step.setParams(fourthCompose.getParams());
        step.setConfig(fourthCompose.getConfig());
        //step.setAssignment(fourthCompose.getAssignments());
        step.setPreScript(fourthCompose.getPreScripts());
        step.setPostScript(fourthCompose.getPostScripts());
        step.setAsserts(fourthCompose.getAsserts());
        return  step;
    }




}
