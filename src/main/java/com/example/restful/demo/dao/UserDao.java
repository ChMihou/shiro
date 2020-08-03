package com.example.restful.demo.dao;

import com.example.restful.demo.enity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;

import java.util.List;

@Mapper
public interface UserDao extends BaseDao<User>{

    public User selectUser(@Param("id")Integer id);

    public Boolean deleteUser(@Param("id")Integer id);

    public int updateUser(User user);

    public int insertUser(User user);

    public List<User> selectAllUser();

    public User selectUserName(@Param("name") String username);


}
