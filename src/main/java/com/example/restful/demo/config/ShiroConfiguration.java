package com.example.restful.demo.config;

import com.example.restful.demo.common.ShiroRealm;
import com.example.restful.demo.security.AdminFormAuthenticationFilter;
import com.example.restful.demo.security.CustomerLogoutFilter;
import com.example.restful.demo.security.ShiroSessionFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfiguration {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 后台身份认证realm;
     * @return
     */
    @Bean(name="adminShiroRealm")
    public ShiroRealm adminShiroRealm(){
        if(logger.isDebugEnabled()){
            logger.debug("ShiroConfiguration.adminShiroRealm()");
        }
        ShiroRealm adminShiroRealm = new ShiroRealm();
        return adminShiroRealm;
    }
    /***
     * shiro的在进行密码验证的时候，将会在此进行匹配
     * @return
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于md5(md5(""));
        //hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);// 表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
        return hashedCredentialsMatcher;
    }

    /**
     * 注入securityManager
     * @see org.apache.shiro.mgt.SecurityManager
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManage(){
        if(logger.isDebugEnabled()){
            logger.debug("ShiroConfiguration.getDefaultWebSecurityManage()");
        }
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();

        Map<String, Object> shiroAuthenticatorRealms = new HashMap<>(5);
        shiroAuthenticatorRealms.put("adminShiroRealm", adminShiroRealm());

        Collection<Realm> shiroAuthorizerRealms = new ArrayList<Realm>();
        shiroAuthorizerRealms.add(adminShiroRealm());
        securityManager.setRealms(shiroAuthorizerRealms);
        securityManager.setSubjectFactory(new DefaultWebSubjectFactory());
        return securityManager;
    }


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        if(logger.isDebugEnabled()){
            logger.debug("ShiroConfiguration.shirFilter()");
        }
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        filterRegistrationBean.setFilter(new ShiroSessionFilter());
        //增加自定义过滤器
        Map<String, Filter> filters = new HashMap<>(5);
        filters.put("admin", new AdminFormAuthenticationFilter());
        filters.put("logout", new CustomerLogoutFilter());
        shiroFilterFactoryBean.setFilters(filters);
        filterRegistrationBean.setFilter(new ShiroSessionFilter());
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        /**
         * anon（匿名）  org.apache.shiro.web.filter.authc.AnonymousFilter
         * authc（身份验证）       org.apache.shiro.web.filter.authc.FormAuthenticationFilter
         * authcBasic（http基本验证）    org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
         * logout（退出）        org.apache.shiro.web.filter.authc.LogoutFilter
         * noSessionCreation（不创建session） org.apache.shiro.web.filter.session.NoSessionCreationFilter
         * perms(许可验证)  org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
         * port（端口验证）   org.apache.shiro.web.filter.authz.PortFilter
         * rest  (rest方面)  org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
         * roles（权限验证）  org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
         * ssl （ssl方面）   org.apache.shiro.web.filter.authz.SslFilter
         * member （用户方面）  org.apache.shiro.web.filter.authc.UserFilter
         * user  表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
         */

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/login/**", "anon");
        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/return/**", "admin");
        filterChainDefinitionMap.put("/hello/**","admin");
        filterChainDefinitionMap.put("/admin/**","admin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
