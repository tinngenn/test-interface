package testNg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.test.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import utils.HttpClientUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class HttpTestNg {




    @Test
    public void createTestCase() throws  Exception{

        Map<String,Object> map = new HashMap<>();
        Map<String,String> header = new HashMap<>();

        map.put("libraryId","1124");
        map.put("name","ddddddd");
        map.put("priority",3);
        map.put("testCaseType","API_AUTO");
        map.put("treeNodeId",3758);

        header.put("Content-Type","application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://192.168.111.151:8000/app-tocean/public/testCase/create";
        // urlStr = "http://localhost:8092/httpGetHtml";
        String resultJson=  httpClientUtil.doPostJson(urlStr,map,header);
        JSONObject jsonObject = JSON.parseObject(resultJson);
        JSONObject obj = jsonObject.getJSONObject("object");
        log.info(resultJson);
        log.info(obj.get("id").toString());
    }


    @Test
    public void createHttpApi(Integer id,String stepJson) throws  Exception{

        Map<String,Object> map = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        map.put("id",162270);
       // map.put("mode","SYNC");
        Map json = (Map) JSONObject.parse(Constants.STEP_TIMEOUT);
       log.info("Step:{}",json.toString());
        List<Map> steps = new ArrayList<>();
        steps.add(json);
        map.put("steps",steps);

        log.info(map.toString());
        header.put("Content-Type","application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://192.168.111.151:8000/app-tocean/public/testCase/apiAutoEdit";
        // urlStr = "http://localhost:8092/httpGetHtml";
        String r=  httpClientUtil.doPostJson(urlStr,map,header);
        log.info(r);

    }
}
