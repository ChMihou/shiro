package com.example.restful.demo.security;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * 覆盖authc的认证
 * @author geekcattle
 */
public class AdminFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    public void setLoginUrl(String loginUrl) {
        super.setLoginUrl("/login/login");
    }

    @Override
    public void setSuccessUrl(String successUrl) {
        super.setSuccessUrl("/admin/index");
    }

}