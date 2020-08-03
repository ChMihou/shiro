package com.example.restful.demo.enity;

import java.io.Serializable;

public class Permission implements Serializable {

    private static final long serialVersionUID = 6421758954026496703L;
    private int id;
    private String permission;
    private String menu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", menu='" + menu + '\'' +
                '}';
    }
}
