package com.example.restful.demo.common;

import com.example.restful.demo.enity.User;
import com.example.restful.demo.service.UserService;
import com.example.restful.demo.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authc.UnknownAccountException;
import javax.annotation.Resource;

public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        if (logger.isDebugEnabled()) {
            logger.info("后台登录：AdminShiroRealm.doGetAuthenticationInfo()");
        }
        String username = (String)token.getPrincipal();

        User user = userService.selectUserName(username);

        String oringnPassword = new String((char[]) token.getCredentials());

        System.out.println("传入的用户名和密码："+username+"  "+oringnPassword);


        String encodedPassword = ShiroUtils.shiroEncryption(oringnPassword,user.getSalt());

        System.out.println("加密后的用户名和密码："+username+"  "+encodedPassword);

        if (user == null){
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                user,
                //密码
                user.getPass(),
                //salt=username+salt
                //不加盐加密
                ByteSource.Util.bytes(user.getName()),
                //realm name
                user.getName()
        );

        return authenticationInfo;
    }

    /**
     * 清空当前用户权限信息
     */
    public void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 认证的数据清除
     * 指定principalCollection 清除
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
