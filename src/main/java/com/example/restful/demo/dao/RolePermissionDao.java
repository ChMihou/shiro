package com.example.restful.demo.dao;

import com.example.restful.demo.enity.RolePermission;

import java.util.List;

public interface RolePermissionDao extends BaseDao<RolePermission>{

    public List<RolePermission> selectList(Integer id);
}
