package com.example.restful.demo.service.impl;

import com.example.restful.demo.dao.RoleDao;
import com.example.restful.demo.enity.Role;
import com.example.restful.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public Role select(Integer id) {
        return roleDao.select(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleDao.selectAll();
    }

    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public Boolean update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public Boolean delete(Integer id) {
        return roleDao.delete(id);
    }
}
