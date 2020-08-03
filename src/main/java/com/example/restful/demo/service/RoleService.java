package com.example.restful.demo.service;

import com.example.restful.demo.enity.Role;

import java.util.List;

public interface RoleService extends BaseService<Role>{

    //查找

    public Role select(Integer id);

    //查找全部
    public List<Role> selectAll();
}
