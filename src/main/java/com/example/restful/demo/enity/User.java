package com.example.restful.demo.enity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class User implements Serializable{


    private static final long serialVersionUID = 3573788959028499397L;

    private Integer uid;

    @NotNull(message = "用户名不能为空")
    private String name;

    @NotNull(message = "密码不能为空")
    private String pass;

    private int role;

    @NotNull(message = "邮箱地址不能为空")
    @Email(message = "邮箱地址格式错误")
    @Length(max = 32,message = "邮箱长度过长，请输入正确邮箱地址")
    private String email;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String phone;

    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
