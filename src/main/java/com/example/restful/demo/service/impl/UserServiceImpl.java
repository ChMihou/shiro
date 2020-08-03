package com.example.restful.demo.service.impl;

import com.example.restful.demo.dao.UserDao;
import com.example.restful.demo.enity.User;
import com.example.restful.demo.service.UserService;
import com.example.restful.demo.utils.MyException;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.restful.demo.utils.ShiroUtils.shiroEncryption;

@Service("usersevice")
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    public User selectUser(@Param("uid")Integer id){
        return userDao.selectUser(id);
    }

    @Override
    public Boolean deleteUser(@Param("uid") Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public User selectUserName(String username) {
        return userDao.selectUserName(username);
    }


    public int postUserInfo(User user) {
//        shiro 自带的工具类生成salt
//        String salt = new SecureRandomNumberGenerator().nextBytes().toString();

        String encodedPassword = shiroEncryption(user.getPass(), user.getName());
        user.setPass(encodedPassword);
        return userDao.insertUser(user);
    }
}
