package com.example.restful.demo.contrller.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {



    @RequestMapping("admin/index")
    @RequiresPermissions("admin:test")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/index");
        return mv;
    }

    @RequestMapping("admin/admin")
    @RequiresPermissions("admin:admin")
    public ModelAndView admin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/admin");
        return mv;
    }

    @RequestMapping("admin/employee")
    @RequiresPermissions("admin:employee")
    public ModelAndView employee(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/employee");
        return mv;
    }

    @RequestMapping("admin/normal")
    @RequiresPermissions("admin:normal")
    public ModelAndView normal(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/normal");
        return mv;
    }

    @RequestMapping("admin/unauthorized")
    public ModelAndView unauthorized()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/unauthorized");
        return mv;
    }
}
