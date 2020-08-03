package com.example.restful.demo.service.impl;

import com.example.restful.demo.dao.PermissionDao;
import com.example.restful.demo.enity.Permission;
import com.example.restful.demo.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionDao permissionDao;

    @Override
    public Integer insert(Permission permission) {
        return permissionDao.insert(permission);
    }

    @Override
    public Boolean update(Permission permission) {
        return permissionDao.update(permission);
    }

    @Override
    public Boolean delete(Integer id) {
        return permissionDao.delete(id);
    }

    @Override
    public Permission select(Integer id) {
        return permissionDao.select(id);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionDao.selectAll();
    }
}
