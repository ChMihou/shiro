package com.example.restful.demo.service.impl;

import com.example.restful.demo.dao.RolePermissionDao;
import com.example.restful.demo.enity.RolePermission;
import com.example.restful.demo.service.RolePermissionService;

import javax.annotation.Resource;
import java.util.List;

public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    RolePermissionDao rolePermissionDao;

    @Override
    public Integer insert(RolePermission rolePermission) {
        return rolePermissionDao.insert(rolePermission);
    }

    @Override
    public Boolean update(RolePermission rolePermission) {
        return rolePermissionDao.update(rolePermission);
    }

    @Override
    public Boolean delete(Integer id) {
        return rolePermissionDao.delete(id);
    }

    @Override
    public RolePermission select(Integer id) {
        return rolePermissionDao.select(id);
    }

    @Override
    public List<RolePermission> selectAll() {
        return rolePermissionDao.selectAll();
    }

    @Override
    public List<RolePermission> selectList(Integer id) {
        return rolePermissionDao.selectList(id);
    }
}
