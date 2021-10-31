package com.example.test.service.mock;


import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistryFactory;
import com.alibaba.fastjson.JSON;
import com.example.test.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.*;


/**
 *  企业商户注册业务流程服务
 *
 * @author shenyuan
 * @version v0.1 2020/08/15 09:18
 */
@Slf4j
@Service
public class TestMockService {


    String  host = "10.20.0.6";

    String  mockHost = "10.10.20.199";

    String  interfaceName= "com.perfma.xcenter.facade.ClientRpcService";

    String  zkHost = "midware.dev-private.perfma-inc.com";
    //String  zkHost = "10.10.220.36";

    /**
     * 尝试mock服务
     * @return 无
     */
    public void testMockAdd( ) {

        MDC.put(Constants.TRACE_LOG_ID,
                UUID.randomUUID().toString().toLowerCase().replace("-", ""));
       //  String host = "172.17.47.68";
      //  String mockHost = "192.168.199.121";


        String method = "getClientInfo";
        int port = 20880;
        String parameters = "";
        String localHost = "0.0.0.0";
        try{
           localHost = InetAddress.getLocalHost().getHostAddress();
           log.info("localHost:{}",localHost);
        }catch (Exception e){log.info("获取本地IP异常！");}

        URL mockUrl = createDeafaultDubboUrl(localHost,port,interfaceName,parameters);
        try {
            Registry newRegistry = createRegistry(zkHost);
            log.info("2222222222222222222");
             registerRouter(interfaceName,host,newRegistry,method,localHost,parameters);
             log.info("注册 mockUrl :{}",mockUrl);
             newRegistry.register(mockUrl);
           // log.info("3333333333333333333");
          //  newRegistry.unregister(mockUrl);
           // findProviders(interfaceName,parameters,newRegistry);

            List<URL> providers = findProviders("com.perfma.xcenter.facade.ClientRpcService\"", null,
                    newRegistry);

      log.info("查找providers:{}",providers.toString());
        }catch(Exception e){
            log.info(e.toString());
        }

    }

    private List<URL> findProviders(String interfaceName, String parameters, Registry newRegistry) {
        URL url = createDeafaultDubboUrl("192.168.199.121", 20880, interfaceName, parameters);
        log.info("lookup url：{}", url);
        List<URL> providers = newRegistry.lookup(url);
        log.info("provider列表：{}", providers);
        return providers;
    }


    /**
     * 尝试mock服务
     * @return 无
     */
    public void testMockDel( ) {

        MDC.put(Constants.TRACE_LOG_ID,
                UUID.randomUUID().toString().toLowerCase().replace("-", ""));
        //  String host = "172.17.47.68";
        //  String mockHost = "192.168.199.121";


        String method = "getClientInfo";
        int port = 20880;

        String parameters = "";

        URL mockUrl = createDeafaultDubboUrl(host,port,interfaceName,parameters);
        log.info("mockUrl:{}",mockUrl.toString());
        try {
            Registry newRegistry = createRegistry(zkHost);

             log.info("3333333333333333333");
             newRegistry.unregister(mockUrl);
        }catch(Exception e){
            log.info(e.toString());
        }

    }


    private void registerRouter(String interfaceName, String host ,Registry newRegistry,String method, String mockHost,String parmeters) {
        List<URL> routerUrls = null;

            routerUrls = createConditionRouter(interfaceName, host, method, mockHost, parmeters);
        if (routerUrls == null || routerUrls.isEmpty()) {
            log.info( "注册失败，生成的路由列表为空: " );
        } else {
            for (URL router : routerUrls) {
                log.info("注册mock服务router：{}", router);
                newRegistry.register(router);
            }
        }
            log.info(routerUrls.toString());

    }

    private Registry createRegistry(String registryAddress) throws Exception {
        String zkIp = registryAddress.contains(":") ? registryAddress.split("[:]")[0]
                : registryAddress;
        int zkPort = registryAddress.contains(":") ? Integer.valueOf(registryAddress.split("[:]")[1])
                : 2181;
        Collection<Registry> registryCollection = ZookeeperRegistryFactory.getRegistries();
        Registry newRegistry = null;
        for (Registry registry : registryCollection) {
            if (zkIp.equals(registry.getUrl().getHost()) && zkPort == registry.getUrl().getPort()) {
                newRegistry = registry;
                break;
            }
        }
        if (newRegistry == null) {
//            newRegistry = new ZookeeperRegistryFactory().createRegistry(new URL("zookeeper", zkIp, zkPort));
            log.warn("目前只支持与mock平台连接同一个注册中心,创建注册中心失败：{}", zkIp + ":" + zkPort);
            //throw new (BizErrorCode.INNER_ERROR, "操作失败,目前只支持与mock平台连接同一个注册中心");
        }
        log.info("创建注册中心成功：{}", newRegistry.getUrl().getHost() + ":" + newRegistry.getUrl().getPort());
        return newRegistry;
    }



    private URL createDeafaultDubboUrl(String host, int port, String interfaceName, String parameters) {
        Map<String, String> map = parameters == null || parameters.isEmpty() ?
                new HashMap<>() :
                JSON.parseObject(parameters, Map.class);
        map.put("interface", interfaceName);
        return new URL("dubbo", host, port, interfaceName, map);
    }

    private List<URL> createConditionRouter(String interfaceName, String conHost, String method, String mockHost, String parameters) {
        Map<String, String> map = parameters == null || parameters.isEmpty() ?
                new HashMap<>() :
               JSON.parseObject(parameters, Map.class);
        map.put("category", "routers");
        map.put("dynamic", "true");
        map.put("name", "mock_condition_router");
//        map.put("runtime", "true");
//        map.put("router", "condition");
//        map.put("priority", "2");
        List<String> ruleList = createRule(conHost, method, mockHost);
        List<URL> routerUrls = new ArrayList<>();
        for (String rule : ruleList) {
            map.put("rule", URL.encode(rule));
            routerUrls.add(new URL("condition", "0.0.0.0", 0, interfaceName, map));
        }
        return routerUrls;

    }

    private List<String> createRule(String conHost, String method, String mockHost) {
        List<String> ruleList = new ArrayList<>();
        StringBuilder ruleSb = new StringBuilder();
        StringBuilder attHostRuleSb = null;
        StringBuilder attMethodRuleSb = null;
        if (conHost != null && !conHost.isEmpty()) {
            ruleSb.append("host=").append(conHost);
            attHostRuleSb = new StringBuilder().append("host!=").append(conHost);
        }
        if (method != null && !method.isEmpty()) {
            if (ruleSb.length() == 0) {
                ruleSb.append("method=").append(method);
            } else {
                ruleSb.append("&method=").append(method);
            }
            attMethodRuleSb = new StringBuilder().append("method!=").append(method);
        }
        ruleSb.append(" => host=").append(mockHost);
//        ruleSb.append(" => port=").append(20880);
        ruleList.add(ruleSb.toString());
        if (attHostRuleSb != null) {
            attHostRuleSb.append(" => host!=").append(mockHost);
//            attHostRuleSb.append(" => port!=").append(20880);
            ruleList.add(attHostRuleSb.toString());
        }
        if (attMethodRuleSb != null) {
            attMethodRuleSb.append(" => host!=").append(mockHost);
//            attMethodRuleSb.append(" => port!=").append(20880);
            ruleList.add(attMethodRuleSb.toString());
        }
        return ruleList;
    }

}
