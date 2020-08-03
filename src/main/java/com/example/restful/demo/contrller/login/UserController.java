package com.example.restful.demo.contrller.login;

import com.example.restful.demo.enity.User;
import com.example.restful.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("rest/{id}")
    public ModelAndView Select(@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView();
        User user = userService.selectUser(id);
        mv.addObject("user",user);
        mv.setViewName("ok");
        return mv;
    }
    @PostMapping("rest")
    public ModelAndView Update(){
        ModelAndView mv = new ModelAndView();
        User u = new User();
        u.setUid(5);
        u.setName("小明123456");
        u.setPass("123456");
        Integer helleo = userService.updateUser(u);
        mv.addObject("user",u);
        mv.addObject("hello",helleo);
        mv.setViewName("ok");
        return mv;
    }
    @DeleteMapping("rest/{id}")
    public ModelAndView Delete(@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView();
        userService.deleteUser(id);
        mv.setViewName("ok");
        return mv;
    }
    @PutMapping("rest")
    public ModelAndView Insert()
    {
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setUid(2);
        user.setName("小明123456");
        user.setPass("123456");
        Integer u = userService.insertUser(user);
        mv.addObject("user",user);
        mv.setViewName("ok");
        return mv;
    }

}
