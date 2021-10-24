package testNg;

import com.example.test.common.DTO.cell.Key;
import com.example.test.common.DTO.cell.tools.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FourthCompose {

    public Key getHello(){
        Key key = new Key();
        key.setHello(123);

        return  key;
    }

    public Key getKey(){
        Key key = new Key();
        key.setKey("Value");

        return  key;
    }


    public  List<Params> getParams(){

        List<Params> params = new ArrayList<>();
        params.add(getParam());
        return  params;
    }

    private  Params getParam(){
        Params params = new Params();
        params.setKey("timeout");
        params.setValue("6000");
        return  params;

    }




    public Config getConfig(){

        Config config = new Config();
        config.setTimeout("5000");
        return  config;
    }


    public  List<Assignment> getAssignments(){

        List<Assignment> assignments = new ArrayList<>();
        assignments.add(getAssignment());
        return  assignments;
    }

    private Assignment getAssignment(){

        Assignment assignment = new Assignment();
       /* assignment.setVar("errmsg");
        assignment.setFn(0);
        //String[] agrs= {""};
        assignment.setArgs(new String[]{"${RESPONSE_BODY}","errmsg"});*/

        assignment.setVar("errmsg");
        assignment.setFn(1);
        //String[] agrs= {""};
        assignment.setArgs(new String[]{"${RESPONSE_BODY}","@type"});
        return  assignment;
    }

    public  List<PreScript> getPreScripts(){

        List<PreScript> preScripts = new ArrayList<>();
        preScripts.add(getPreScript());
        return  preScripts;
    }

    private  PreScript getPreScript(){

        PreScript preScript = new PreScript();
        preScript.setType("JavaScript");
        preScript.setTimeout(10000);
        preScript.setScript("\n" +
                "var timestamp = new Date().getTime();\n" +
                "sys.set(\"timestamp\", timestamp);");

        return  preScript;

    }

    public  List<PostScript> getPostScripts(){

        List<PostScript> postScripts = new ArrayList<>();
        postScripts.add(getPostScript());
        return  postScripts;
    }


    private  PostScript getPostScript(){

        PostScript postScript = new PostScript();
        postScript.setType("JavaScript");
        postScript.setTimeout(10000);
        postScript.setScript("var body = post.getBody();\n" +
                "console.info(body);\n" +
                "var bodyAsBuffer = post.getBodyAsBuffer();\n" +
                "console.info(bodyAsBuffer);\n" +
                "var statusCode = post.getStatusCode();\n" +
                "console.info(statusCode);\n" +
                "var statusMessage = post.getStatusMessage();\n" +
                "console.info(statusMessage);\n" +
                "var header = post.getHeaders();\n" +
                "console.info(header);\n" +
                "var timestamp = new Date().getTime();\n" +
                "console.info(timestamp);\n" +
                "var a = sys.get(\"var\");\n" +
                "console.info(a);");


        return  postScript;

    }


    public  List<Assert> getAsserts(){

        List<Assert> asserts = new ArrayList<>();
        asserts.add(getAssert());
        return  asserts;
    }



    private  Assert getAssert() {

        Assert asser = new Assert();
        asser.setFn(0);
        asser.setSource("${RESPONSE_CODE}");
        asser.setTarget("200");
        return  asser;
    }


}
