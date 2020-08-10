package com.example.restful.demo.contrller.login;

import com.example.restful.demo.common.shiro.security.CustomerAuthenticationToken;
import com.example.restful.demo.enity.User;
import com.example.restful.demo.service.UserService;
import com.example.restful.demo.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @RequestMapping("login/login")
    public ModelAndView Login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/loginauth");
        return mv;
    }

    @RequestMapping("return/ok")
    public ModelAndView ok() {
        ModelAndView mv = new ModelAndView();
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println(session.getTimeout());
        mv.setViewName("return/ok");
        return mv;
    }

    @RequestMapping(value="login")
    public ModelAndView loginForm(){
        ModelAndView mv = new ModelAndView();
        if(ShiroUtils.isLogin()){
            mv.setViewName("redirect:login/index");
            return mv;
        }
        mv.setViewName("login/loginauth");
        return mv;
    }

    @RequestMapping("login/error")
    public ModelAndView error(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/error");
        return mv;
    }


    @RequestMapping("login/loginshiro")
    public ModelAndView LoginShiro(String name,String pass, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView();
        System.out.println(name+pass);
        String username = name;
        CustomerAuthenticationToken customerAuthenticationToken = new CustomerAuthenticationToken(name, pass, false);
        Subject subject = SecurityUtils.getSubject();
        try {

            subject.login(customerAuthenticationToken);

        } catch (
                UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
            mv.setViewName("login/error");
        } catch (
                IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
            mv.setViewName("login/error");
        } catch (
                ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
            mv.setViewName("login/error");
        } catch (
                AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
            mv.setViewName("login/error");
        }
        //验证是否登录成功
        if(subject.isAuthenticated()){
            Session session = SecurityUtils.getSubject().getSession();
            System.out.println(session.getTimeout());
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            mv.setViewName("admin/index");
        }
        return mv;
    }

    @RequestMapping("login/loginauth")
    public ModelAndView loginshiro(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/loginauth");
        return mv;
    }

    @RequestMapping("hello/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("return/ok");
        String username = "小绿";
        String email = "123@qq.com";
        String phone = "123456";
        User user = new User();
        user.setEmail(email);
        user.setPhone(phone);
        for (int i = 1;i<4;i++){
            user.setName(username+i);
            String password = ShiroUtils.shiroEncryption("123456",username+i);
            user.setPass(password);
            user.setRole(i);
            user.setSalt(username+i);
            userService.insertUser(user);
        }
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println(session.getTimeout());
        return mv;
    }

    @RequestMapping("login/logout")
    public ModelAndView logout(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/logout");
        return mv;
    }
}
