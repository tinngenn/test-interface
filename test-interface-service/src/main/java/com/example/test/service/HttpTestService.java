package com.example.test.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.common.Constants;
import com.example.test.dubbo.api.result.OrgMerRegResult;
import com.example.test.dubbo.api.dubboResult.Result;
import com.example.test.service.dto.AhupDeductBO;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class HttpTestService {


  public String getDtoToJson(Map<String,String> parmMap,SerializerFeature... serializerFeature){
        Result<List<AhupDeductBO>> result= new Result<>();
        List<AhupDeductBO> resultList= new ArrayList();

        Integer count = 1;

      try{
          if(parmMap.containsKey("count") && StringUtils.isNotEmpty(parmMap.get("count"))){
              count = Integer.valueOf(parmMap.get("count"));
          }
          for(Integer i=0; i<count; i++){
              resultList.add(getAhupDeductBO(parmMap));
          }

        result.setResult(resultList);
      }catch (Exception e){result.setPrimaryErrorCode("A000001"); result.setPrimaryErrorMsg("存在参数类型配置错误");}
        String str = JSONObject.toJSONString(result,
                  serializerFeature);

     /* SerializerFeature.WriteClassName,
              SerializerFeature.SortField,
              SerializerFeature.WriteNullStringAsEmpty*/

       log.info("Result_JON:{}", str);
               return  str;
    }



    public String getDtoToJson(Integer count,String id,Map<String,String> parmMap){
        if(StringUtils.isNotEmpty(id)){
            parmMap.put("ID",id);
        }

        String str = getDtoToJson(parmMap,SerializerFeature.WriteClassName,
                SerializerFeature.SortField,   SerializerFeature.WriteNullStringAsEmpty);
        log.info("Result_JON:{}", str);
        return  str;
    }


    public String getDtoToJsonRecursion(){
        Result<AhupDeductBO> result = new Result<>();
        AhupDeductBO ahupDeductBO = new AhupDeductBO();


        OrgMerRegResult orgMerRegResult3 = new OrgMerRegResult();
        orgMerRegResult3.setLoginNo("18016078963");
        orgMerRegResult3.setRosefinchUsername("SHY1");
        orgMerRegResult3.setLoginNo("l3717922903");
        orgMerRegResult3.setMerchantCode("31711111113");
        orgMerRegResult3.setMerchantName("递归测试商户名3");
        orgMerRegResult3.setMerchantShortName("递归商户简称3");
        orgMerRegResult3.setProdCode("81733373");
        orgMerRegResult3.setOperatorNo("317222223");
        orgMerRegResult3.setContactPhone("1370000003");
        orgMerRegResult3.setContractNo("333333333333");
        orgMerRegResult3.setTraceLogId(MDC.get(Constants.TRACE_LOG_ID));


        OrgMerRegResult orgMerRegResult2 = new OrgMerRegResult();
        orgMerRegResult2.setLoginNo("18016078960");
        orgMerRegResult2.setRosefinchUsername("SHY");
        orgMerRegResult2.setLoginNo("l3717922901");
        orgMerRegResult2.setMerchantCode(JSONObject.toJSONString(orgMerRegResult3,
                SerializerFeature.SortField));
        orgMerRegResult2.setMerchantName("递归测试商户名2");
        orgMerRegResult2.setMerchantShortName("递归商户简称2");
        orgMerRegResult2.setProdCode("81733372");
        orgMerRegResult2.setOperatorNo("317222222");
        orgMerRegResult2.setContactPhone("1370000002");
        orgMerRegResult2.setContractNo("333333333333");



        OrgMerRegResult orgMerRegResult = new OrgMerRegResult();
        orgMerRegResult.setLoginNo("18016078959");
        orgMerRegResult.setRosefinchUsername("ShenYuan");
        orgMerRegResult.setLoginNo("l3717922900");
        orgMerRegResult.setMerchantCode(JSONObject.toJSONString(orgMerRegResult2,
                SerializerFeature.SortField));
        orgMerRegResult.setMerchantName("递归测试商户名");
        orgMerRegResult.setMerchantShortName("递归商户简称");
        orgMerRegResult.setProdCode("81733371");
        orgMerRegResult.setOperatorNo("317222221");
        orgMerRegResult.setContactPhone("1370000001");
        orgMerRegResult.setContractNo("22222222");
        orgMerRegResult.setTraceLogId(MDC.get(Constants.TRACE_LOG_ID));




        ahupDeductBO.setOrderNo(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        //ahupDeductBO.setOrderNo(DateTime.now().toString("yyyyMMddHHmmss"));
        ahupDeductBO.setPayOrgCode("ICBC");
        ahupDeductBO.setBankRespTime("222222222");
        ahupDeductBO.setBizStsCd("3333333333");
        ahupDeductBO.setInnerMercNo("4444444444444");
        ahupDeductBO.setBizStsDesc("测试");
        ahupDeductBO.setStatus(true);
        ahupDeductBO.setBizStsDesc(JSONObject.toJSONString(orgMerRegResult,
                SerializerFeature.SortField));

        result.setResult(ahupDeductBO);
        String str = JSONObject.toJSONString(result,
                SerializerFeature.SortField,
                SerializerFeature.WriteClassName,SerializerFeature.NotWriteRootClassName);
        log.info("Result_JON:{}", str);
        return  str;
    }


    public  AhupDeductBO getAhupDeductBO(Map<String,String> parmMap){

        AhupDeductBO ahupDeductBO = new AhupDeductBO();
        ahupDeductBO.setOrderNo(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));

        //ID参数
        if(StringUtils.isNotEmpty(parmMap.get("ID"))){
            ahupDeductBO.setId(parmMap.get("ID"));
        }

        //PayOrgCode参数
        if(StringUtils.isNotEmpty(parmMap.get("payOrgCode"))){
            ahupDeductBO.setPayOrgCode(parmMap.get("payOrgCode"));
        }
        else {
            ahupDeductBO.setPayOrgCode("ICBC");
        }

        //innerMercNo参数
        if(StringUtils.isNotEmpty(parmMap.get("innerMercNo"))){
            ahupDeductBO.setInnerMercNo(String.valueOf(parmMap.get("innerMercNo")));
        }
        else {
            ahupDeductBO.setInnerMercNo("1111111111");
        }

        //SignNo协议号
        if(StringUtils.isNotEmpty(parmMap.get("signNo"))){
            ahupDeductBO.setSignNo(Integer.valueOf(parmMap.get("signNo")));
        }
        else {
            ahupDeductBO.setSignNo(222222);
        }

        //amount金额
        if(StringUtils.isNotEmpty(parmMap.get("amount"))){
            ahupDeductBO.setAmount(Long.valueOf(parmMap.get("amount")));
        }
        else {
            ahupDeductBO.setAmount(3333333333l);
        }

        //bankRespTime响应时间
        if(StringUtils.isNotEmpty(parmMap.get("bankRespTime"))){
            ahupDeductBO.setBankRespTime(String.valueOf(parmMap.get("bankRespTime")));
        }
        else {
            ahupDeductBO.setBankRespTime("4444444444");
        }

        //status金额
        if(StringUtils.isNotEmpty(parmMap.get("status"))){
            ahupDeductBO.setStatus(Boolean.valueOf(parmMap.get("status")));
        }
        else {
            ahupDeductBO.setStatus(true);
        }

        //bizStsCd金额
        if(StringUtils.isNotEmpty(parmMap.get("bizStsCd"))){
            ahupDeductBO.setBizStsCd(String.valueOf(parmMap.get("bizStsCd")));
        }
        else {
            ahupDeductBO.setBizStsCd("5555555555");
        }

        //bizStsDesc结果详情
        if(StringUtils.isNotEmpty(parmMap.get("bizStsDesc"))){
            ahupDeductBO.setBizStsDesc(String.valueOf(parmMap.get("bizStsDesc")));
        }
        else {
            ahupDeductBO.setBizStsDesc("666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666");
        }

        //payRespNo响应流水号
        if(StringUtils.isNotEmpty(parmMap.get("payRespNo"))){
            ahupDeductBO.setPayRespNo(String.valueOf(parmMap.get("payRespNo")));
        }
        else {
            ahupDeductBO.setPayRespNo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }

        //count 计数
        if(parmMap.containsKey("count") && StringUtils.isNotEmpty(parmMap.get("count"))){
            ahupDeductBO.setCount(Integer.valueOf(parmMap.get("count")));
        }
        else {
            ahupDeductBO.setCount(1);
        }


        ahupDeductBO.setTraceLogId(MDC.get(Constants.TRACE_LOG_ID));
        List<Object> list = new ArrayList<Object>();
        list.add("啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
        list.add("bbbbbb");
        list.add(111111);
        list.add(222222);
        list.add(true);
        list.add(false);
        ahupDeductBO.setObjList(list);
        return  ahupDeductBO;
    }


    public  String createXml(Map<String,String> parmMap){
        StringWriter sw = new StringWriter();
        try {
            // 创建解析器工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            // 不显示standalone="no"
            document.setXmlStandalone(true);
            Element universe = document.createElement("universe");
            // 向bookstore根节点中添加子节点book
            Element god = document.createElement("god");
            Element king = document.createElement("king");
            Element general = document.createElement("general");
            Element human0 = document.createElement("human");
            Element human1 = document.createElement("human");
            Element human2= document.createElement("human");
            Element human3 = document.createElement("human");
            Element human4 = document.createElement("human");

            // 不显示内容 name.setNodeValue("不好使");


            universe.setTextContent(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
            god.setTextContent("雷神");
            king.setTextContent("亚历山大");
            general.setTextContent("巴顿");
            human0.setTextContent("张零");
            human1.setTextContent("张一");
            human2.setTextContent("张二");
            human3.setTextContent("张三");
            human4.setTextContent("张四");

            // 为book节点添加属性
            universe.setAttribute("id", "1");
            universe.setAttribute("leve", "A1");
            universe.setAttribute("traceLogId", MDC.get(Constants.TRACE_LOG_ID));
            god.setAttribute("id", "2");
            god.setAttribute("leve", "B1");

            king.setAttribute("id", "3");
            king.setAttribute("leve", "C1");
            general.setAttribute("id", "4");
            general.setAttribute("leve", "D1");
            human0.setAttribute("id", "5");
            human0.setAttribute("time", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
            human1.setAttribute("id", "6");
            human1.setAttribute("time", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
            human2.setAttribute("id", "7");
            human2.setAttribute("time", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
            human3.setAttribute("id", "8");
            human3.setAttribute("time", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
            human4.setAttribute("id", "9");
            human4.setAttribute("time", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));

            // 将book节点添加到bookstore根节点中
            universe.appendChild(god);
            god.appendChild(king);
            king.appendChild(general);
            general.appendChild(human0);
            general.appendChild(human1);
            general.appendChild(human2);
            general.appendChild(human3);
            general.appendChild(human4);
            universe.appendChild(god);

            //count 计数
            if(parmMap.containsKey("count") && StringUtils.isNotEmpty(parmMap.get("count"))){
                for(Integer i=0; i<Integer.valueOf(parmMap.get("count")); i++){
                    universe.appendChild(god.cloneNode(true));
                }
            }

            // 将bookstore节点（已包含book）添加到dom树中
            document.appendChild(universe);

            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建 Transformer对象
            Transformer tf = tff.newTransformer();

            // 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            // 创建xml文件并写入内容
            String context="";
            DOMSource domSource =  new DOMSource(document);

            StreamResult xmlResult = new StreamResult(sw);

            tf.transform(domSource, xmlResult);

            log.info("XML:{}",sw.toString());
            System.out.println("生成book1.xml成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成book1.xml失败");
        }
        return  sw.toString();
    }

}
