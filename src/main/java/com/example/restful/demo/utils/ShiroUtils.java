package com.example.restful.demo.utils;

import com.example.restful.demo.enity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroUtils {
    /***
     * 对用户的密码进行MD5加密
     * 做成工具类
     * @Parm password：用户密码
     * @Parm salt: 加密盐值
     */
    public static String shiroEncryption(String password,String salt) {

        // shiro 自带的工具类生成salt
        //String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 加密次数
        int times = 2;
        // 算法名称
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();

        // 返回加密后的密码
        return encodedPassword;
    }
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static Boolean isLogin(){
        return getSubject().isAuthenticated();
    }

    /**
     * 获取session信息
     * @return
     */
    public static Session getSession(){
        try{
            Session session = getSubject().getSession();
            if (session == null){
                session = getSubject().getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户信息
     * @return
     */
    public static User getUserInfo(){
        try {
            if(getSession() != null){
                User admin = (User) getSubject().getPrincipals().getPrimaryPrincipal();
                return admin;
            }else{
                return null;
            }
        }catch (Exception e){

        }
        return null;
    }
}
