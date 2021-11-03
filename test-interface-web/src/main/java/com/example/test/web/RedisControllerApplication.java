package com.example.test.web;


import com.example.test.utils.CotrollerFuction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class RedisControllerApplication {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    CotrollerFuction cotrollerFuction;

    @RequestMapping(value="/redisTemplate" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String httpGetHtml(Model model, HttpServletRequest httpRequest) {
        cotrollerFuction.printMessage(httpRequest);
       // redisTemplate.opsForValue().set("author", "Damein_xym",10, TimeUnit.SECONDS);
        //redisTemplate.opsForValue().get("author");
        //redisTemplate.delete("author");
        return    redisTemplate.opsForValue().get("author").toString();

    }
}