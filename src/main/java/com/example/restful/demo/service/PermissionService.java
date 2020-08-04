package com.example.restful.demo.service;

import com.example.restful.demo.enity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PermissionService extends BaseService<Permission>{
    public List<String> selectUserAll(@Param("username") String username);
}
