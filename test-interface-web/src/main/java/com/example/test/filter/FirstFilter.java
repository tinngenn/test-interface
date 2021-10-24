package com.example.test.filter;


import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2020/9/1.
 */

@Slf4j
@WebFilter(filterName="firstFilter", urlPatterns="/*")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("拦截器添加 Hearders");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authentication, content-type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","ddddddddddddddddddd");
        response.setHeader("1111111","22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");

        response.setCharacterEncoding("UTF-8");
        String method = request.getMethod();
       /* if(method.equalsIgnoreCase("OPTIONS")){
            servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
        }else{*/
           filterChain.doFilter(servletRequest, servletResponse);
       // }
    }

    @Override
    public void destroy() {

    }
}

