package com.example.test.service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.test.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import utils.HttpClientUtil;

import java.util.*;


@Slf4j
@Service
public class ApiOptionService implements Runnable {

    private Map<String, String> parmMap;
    private Integer id = 0;

    public ApiOptionService(Map<String, String> parmMap) {

        this.parmMap = parmMap;

    }

    public void setInt(Integer id) {
        this.id = id;
    }


    //创建测试用例
    public void createTestCase() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, String> header = new HashMap<>();

        map.put("libraryId", Integer.valueOf(parmMap.get("libraryId")));
        map.put("name", parmMap.get("name") + "_" + String.valueOf(id));
        map.put("priority", Integer.valueOf(parmMap.get("priority")));
        map.put("testCaseType", "API_AUTO");
        map.put("treeNodeId", Integer.valueOf(parmMap.get("treeNodeId")));

        header.put("Content-Type", "application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://" + parmMap.get("host") + "/app-tocean/public/testCase/create";
        // urlStr = "http://localhost:8092/httpGetHtml";

        for (int i = 0; i < 10; i++) {
            String resultJson = httpClientUtil.doPostJson(urlStr, map, header);
            JSONObject jsonObject = JSON.parseObject(resultJson);
            if (Boolean.valueOf(jsonObject.get("success").toString())) {
                JSONObject obj = jsonObject.getJSONObject("object");
                log.info(resultJson);
                log.info(obj.get("id").toString());
                createHttpApi(parmMap.get("host"), Integer.valueOf(obj.get("id").toString()), parmMap.get("stepsJson"));
                break;
            } else {
                log.info("66666666666666666666666666666");
                Thread.sleep(2000);
            }
        }
   /*     for(int i=1;i<=count;i++){


            createHttpApi(pramMap.get("host") ,Integer.valueOf(obj.get("id").toString()),pramMap.get("stepsJson"));
            Thread.sleep(500);
        }*/

        //createHttpApi(parmMap.get("host") ,Integer.valueOf(obj.get("id").toString()),parmMap.get("stepsJson"));
    }

    //创建http配置
    public void createHttpApi(String host, Integer id, String stepJson) throws Exception {

        Map<String, Object> map = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        map.put("id", id);
        // map.put("mode","SYNC");
        Map json = (Map) JSONObject.parse(stepJson);

        json.put("id", UUID.randomUUID().toString());
        log.info("Step:{}", json.toString());
        List<Map> steps = new ArrayList<>();
        steps.add(json);
        map.put("steps", steps);

        log.info(map.toString());
        header.put("Content-Type", "application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://" + host + "/app-tocean/public/testCase/apiAutoEdit";
        // urlStr = "http://localhost:8092/httpGetHtml";

        for (int i = 0; i < 10; i++) {
            String resultJson = httpClientUtil.doPostJson(urlStr, map, header);
            JSONObject jsonObject = JSON.parseObject(resultJson);
            log.info(resultJson);
            if (Boolean.valueOf(jsonObject.get("success").toString())) {
                json.put("id", UUID.randomUUID().toString());
                log.info("Step:{}", json.toString());
                steps.remove("steps");
                steps.add(json);
                map.put("steps", steps);
                log.info(resultJson);
                break;
            } else {
                Thread.sleep(5000);
            }
        }
    }

    @Override
    public void run() {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
      /*  try {
            //  Thread.sleep(300);
            createTestCase();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // System.out.println(String.format("打印消息%s", message));
    }

}


