package com.example.restful.demo.common;

import com.example.restful.demo.enity.User;
import com.example.restful.demo.service.PermissionService;
import com.example.restful.demo.service.UserService;
import com.example.restful.demo.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
         * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
         * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
         * 缓存过期之后会再次执行。
         */
        logger.info("后台权限校验-->AdminShiroRealm.doGetAuthorizationInfo()");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User admin = (User) principals.getPrimaryPrincipal();
        Set<String> menus = null;
        menus = permissionService.selectUserAll(admin.getName()).stream().collect(Collectors.toSet());
        System.out.println(menus);
        authorizationInfo.setStringPermissions(menus);
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        if (logger.isDebugEnabled()) {
            logger.info("后台登录：AdminShiroRealm.doGetAuthenticationInfo()");
        }
        String username = (String) token.getPrincipal();

        User user = userService.selectUserName(username);

        String oringnPassword = new String((char[]) token.getCredentials());

        System.out.println("传入的用户名和密码：" + username + "  " + oringnPassword);


        String encodedPassword = ShiroUtils.shiroEncryption(oringnPassword, user.getSalt());

        System.out.println("加密后的用户名和密码：" + username + "  " + encodedPassword);

        if (user == null) {
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                user,
                //密码
                user.getPass(),
                //salt=username+salt
                //加盐加密
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
