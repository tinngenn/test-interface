
import com.example.test.common.DTO.cell.*;
import testNg.BaseTestNG;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.common.DTO.ExecuteObject;
import com.example.test.common.DTO.cell.tools.Assignment;
import com.example.test.common.DTO.cell.tools.PostScript;
import com.example.test.common.DTO.cell.tools.PreScript;
import com.example.test.common.DTO.cell.tools.Config;
import com.example.test.dubbo.api.request.OrgMerchantRegisteDTO;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class TestClass  extends BaseTestNG {


    @Test
    public void test(){

        ExecuteObject executeObject = new ExecuteObject();
        /*  Env设置开始  */
        Env env = new Env();
        Variable variableEnv = new Variable();
        Key key123= new  Key();
        key123.setHello(123);
        variableEnv.setKey(key123);
        env.setName("test");
        env.setVariable(variableEnv);
        /*  Env设置结束  */

        /*  Execute 设置开始  */
        List<Execute> executes = new ArrayList<>();
        Execute execute1 = new Execute();
        execute1.setEvents(11);
        execute1.setMode(1);
        Variable variableExecute = new Variable();
        Key keyValue = new  Key();
        keyValue.setKey("value");
        variableExecute.setKey(keyValue);
        execute1.setVariable(variableExecute);


        /*  Steps 设置开始  */
        List<Steps> steps = new ArrayList<>();
        Steps step1 = new Steps();
        step1.setType(1);
        step1.setMethod("POST");
        step1.setHostname("10.10.220.36");
        step1.setPath("/httpGetHtml");
        step1.setProtocol("http");
        step1.setPort(8092);

        /*  Config 设置开始  */
        Config config = new Config();

        step1.setConfig(config);
        /*  Config 设置开始  */


        /*  Assignment 设置开始  */
        List<Assignment> assignments = new ArrayList<>();
        Assignment assignment = new Assignment();
        assignment.setVar("errmsg");
        assignment.setFn(0);
        String[] agrs= {""};
        assignment.setArgs(new String[]{"${RESPONSE_BODY}","errmsg"});
        assignments.add(assignment);
        step1.setAssignment(assignments);
        /*  Assignment 设置结束  */

        /*  prsScript 设置开始  */
        List<PreScript> preScripts = new ArrayList<>();
        PreScript preScript = new PreScript();
        preScript.setType("JavaScript");
        preScript.setTimeout(10000);
        preScript.setScript("\n" +
                "var timestamp = new Date().getTime();\n" +
                "sys.set(\"timestamp\", timestamp);");
        preScripts.add(preScript);
        step1.setPreScript(preScripts);
        /*  prsScript 设置结束  */



        /*  PostScript 设置开始  */
        List<PostScript> postScripts = new ArrayList<>();
        PostScript postSpcript = new PostScript();
        postSpcript.setType("JavaScript");
        postSpcript.setTimeout(10000);
        postSpcript.setScript("var body = post.getBody();\n" +
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
        postScripts.add(postSpcript);
        step1.setPostScript(postScripts);
        /*  PostScript 设置结束  */


        steps.add(step1);
        execute1.setSteps(steps);
        /*  Steps 设置结束  */


        /*  Execute设置结束  */
        executeObject.setEnv(env);
        executes.add(execute1);
        executeObject.setExecute(executes);

        String str = JSONObject.toJSONString(executeObject,
                SerializerFeature.PrettyFormat,
                SerializerFeature.SortField
                );

        //把内容保存入剪切板
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(str), null);
        log.info("str:\n {}" ,str);
    }


    @Test
    public void tttttt(){

        OrgMerchantRegisteDTO orgMerchantRegisteDTO = new OrgMerchantRegisteDTO();
        orgMerchantRegisteDTO.setLoginNo("18016078959");
        orgMerchantRegisteDTO.setRosefinchUsername("shenyuan");
        orgMerchantRegisteDTO.setTraceLogId("FFSD8FDF8H999");
        String str = JSONObject.toJSONString(orgMerchantRegisteDTO,
                SerializerFeature.PrettyFormat,
                SerializerFeature.SortField

        );
        log.info("str:\n {}" ,str);
    }


    @Test
    public void list(){

        List<String> listStr = new ArrayList<>();
        listStr.add("111111");
        listStr.add("222222");
        listStr.add("333333");
        String str = JSONObject.toJSONString(listStr,
                SerializerFeature.PrettyFormat,
                SerializerFeature.SortField

        );
        log.info("listStr:\n {}" ,str);
    }

    @Test
    public void arrey(){

        String[] strArry={"AAA","BBB","CCC"};
        String str = JSONObject.toJSONString(strArry,
                SerializerFeature.PrettyFormat,
                SerializerFeature.SortField

        );
        log.info("listStr:\n {}" ,str);
    }

}
