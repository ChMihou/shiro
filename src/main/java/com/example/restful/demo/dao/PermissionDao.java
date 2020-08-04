package com.example.restful.demo.dao;

import com.example.restful.demo.enity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao extends BaseDao<Permission>{

    public  List<String> selectUserAll(@Param("username") String username);
}
