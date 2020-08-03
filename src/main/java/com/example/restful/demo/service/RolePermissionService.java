package com.example.restful.demo.service;

import com.example.restful.demo.enity.RolePermission;

import java.util.List;

public interface RolePermissionService extends BaseService<RolePermission> {
    public List<RolePermission> selectList(Integer id);
}
