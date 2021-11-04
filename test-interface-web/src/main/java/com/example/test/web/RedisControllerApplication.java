package com.example.test.web;


import com.example.test.utils.CotrollerFuction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class RedisControllerApplication {


    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    CotrollerFuction cotrollerFuction;

    @RequestMapping(value = "/redisTemplate/string/get", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String redisStringGet(Model model, HttpServletRequest httpRequest) {
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);


        if (parmMap.containsKey("redisKey") && StringUtils.isNotEmpty(parmMap.get("redisKey"))) {
            String redisKey = parmMap.get("redisKey").trim();
            log.info(redisKey);
            // redisTemplate.opsForValue().get("author");
            //redisTemplate.delete("author");
            if (null != redisTemplate.opsForValue().get(redisKey)) {
                return redisTemplate.opsForValue().get(redisKey).toString();
            } else {
                return "redieValue 为 null";
            }


        } else {
            return "redisKey不能为null";
        }

    }


    @RequestMapping(value = "/redisTemplate/string/set", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String redisStringSet(Model model, HttpServletRequest httpRequest) {
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);

        try {
            if (parmMap.containsKey("redisKey") && StringUtils.isNotEmpty(parmMap.get("redisKey"))) {
                String redisKey = parmMap.get("redisKey").trim();
                if (parmMap.containsKey("redisValue") && StringUtils.isNotEmpty(parmMap.get("redisValue"))) {
                    String redisValue = parmMap.get("redisValue").trim();
                    if (parmMap.containsKey("redisTimeout") && StringUtils.isNotEmpty(parmMap.get("redisTimeout"))) {
                        Long redisTimeout = Long.valueOf(parmMap.get("redisTimeout").trim());

                        redisTemplate.opsForValue().set(redisKey, redisValue, redisTimeout, TimeUnit.SECONDS);
                        return "redis 赋值成功";
                    }
                }
            }
        }catch (Exception e){
            return "Redis赋值异常!" + e;
        }
       return "redisKey, redisValue, redisTimeout不能为null";
    }
}
