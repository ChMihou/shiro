package com.example.restful.demo.service;

import com.example.restful.demo.enity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public User selectUser(@Param("uid")Integer id);

    public Boolean deleteUser(@Param("uid")Integer id);

    public int updateUser(User user);

    public int insertUser(User user);

    public List<User> selectAllUser();

    public User selectUserName(@Param("name") String username);
}
