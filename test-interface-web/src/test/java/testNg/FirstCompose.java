package testNg;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.common.DTO.ExecuteObject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;


@Slf4j
public class FirstCompose {

    ExecuteObject executeObject = new ExecuteObject();
    SecondCompose secondCompose = new SecondCompose();

    @Test
    public void ttt(){

        executeObject.setEnv(secondCompose.getEnv());
        executeObject.setExecute(secondCompose.getExecutes());

        String str = JSONObject.toJSONString(executeObject,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteClassName,
                SerializerFeature.SortField,SerializerFeature.DisableCheckSpecialChar);

        str = str.replace("asserts","assert");





        //把内容保存入剪切板
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(str), null);


        log.info("str:\n {}" ,str);


    }

}
