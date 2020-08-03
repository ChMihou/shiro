package com.example.restful.demo.interceptor;

import com.example.restful.demo.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class interceptor implements HandlerInterceptor {
    private static final String USER_AGENT = "user-agent";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        System.out.println(uri+IpUtil.getIpAddr(request));
        System.out.println(String.format("用户访问地址: %s, 来路地址: %s", uri, IpUtil.getIpAddr(request)));
        return true;
    }
}
