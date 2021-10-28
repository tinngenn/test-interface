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

    //查询执行机详情
    @Test
    public void engineDetail() throws  Exception{

        Map<String,Object> map = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        map.put("xengineId","2");
        log.info(map.toString());
        header.put("Content-Type","application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://192.168.111.249:8000/api/tocean/public/engine/detail";
        // urlStr = "http://localhost:8092/httpGetHtml";
        String r=  httpClientUtil.doPostJson(urlStr,map,header);
        log.info("11:{}",r);

    }


    //查询当前环境有无执行机
    @Test
    public void engineHasEngine() throws  Exception{

        Map<String,Object> map = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        map.put("envId",0);
        log.info(map.toString());
        header.put("Content-Type","application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://192.168.111.249:8000//api/tocean/public/engine/hasEngine";
        // urlStr = "http://localhost:8092/httpGetHtml";
        String r=  httpClientUtil.doPostJson(urlStr,map,header);
        log.info("11:{}",r);

    }

    //查询查询执行机分组
    @Test
    public void engineQueryGroup() throws  Exception{

        Map<String,Object> map = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        map.put("no",1);
        log.info(map.toString());
        header.put("Content-Type","application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://192.168.111.249:8000/api/tocean/public/engine/queryGroup";
        // urlStr = "http://localhost:8092/httpGetHtml";
        String resultJson=  httpClientUtil.doPostJson(urlStr,map,header);
        JSONObject jsonObject = JSON.parseObject(resultJson);
        List<Map<String,Object>>  mapList = (List<Map<String, Object>>) jsonObject.get("object");
        for(Map<String,Object> mapTemp : mapList){
            log.info(":{}",mapTemp.toString());
        }

    }


    //查询执行机列表
    @Test
    public void engineEngine() throws  Exception{

        Map<String,Object> map = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        map.put("current",1);
        map.put("envId",0);
        map.put("size",10);
        log.info(map.toString());
        header.put("Content-Type","application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://192.168.111.249:8000/api/tocean/public/engine/page";
        // urlStr = "http://localhost:8092/httpGetHtml";
        String resultJson=  httpClientUtil.doPostJson(urlStr,map,header);
        //JSONObject jsonObject = JSON.parseObject(resultJson);
        log.info(resultJson);

    }

    //获取执行机下载列表
    @Test
    public void engineGetDownloadList() throws  Exception{

        Map<String,Object> map = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        map.put("envId",1);
        log.info(map.toString());
        header.put("Content-Type","application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://192.168.111.249:8000/api/tocean/public/engine/getDownloadList";
        // urlStr = "http://localhost:8092/httpGetHtml";
        String resultJson=  httpClientUtil.doPostJson(urlStr,map,header);
        JSONObject jsonObject = JSON.parseObject(resultJson);
        List<Map<String,Object>>  mapList = (List<Map<String, Object>>) jsonObject.get("object");
        for(Map<String,Object> mapTemp : mapList){
            log.info(":{}",mapTemp.toString());
        }
    }


    //下载执行机配置文件
    @Test
    public void engineDownloadConfig() throws  Exception{

        Map<String,Object> map = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        map.put("envId",1);
        log.info(map.toString());
        header.put("Content-Type","application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://192.168.111.249:8000/api/tocean/public/engine/downloadConfig";
        // urlStr = "http://localhost:8092/httpGetHtml";
        String resultJson=  httpClientUtil.doPostJson(urlStr,map,header);
        //JSONObject jsonObject = JSON.parseObject(resultJson);
        log.info(resultJson);

    }


    //下载执行机
    @Test
    public void engineDownload() throws  Exception{

        Map<String,Object> map = new HashMap<>();
        Map<String,String> header = new HashMap<>();
        map.put("fileStoreId",153);
        log.info(map.toString());
        header.put("Content-Type","application/json");
        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        String urlStr = "http://192.168.111.249:8000/api/tocean/public/engine/download";
        // urlStr = "http://localhost:8092/httpGetHtml";
         httpClientUtil.doDownload(urlStr,header,map,"D:\\TWhale-1.0-SNAPSHOT-linux-x64.tar.gz");

    }
}
