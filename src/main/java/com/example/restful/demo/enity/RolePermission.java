package com.example.restful.demo.enity;

import java.io.Serializable;

public class RolePermission implements Serializable {

    private static final long serialVersionUID = 3240997146082659770L;
    private int id;
    private int roleid;
    private int permissionid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(int permissionid) {
        this.permissionid = permissionid;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", roleid=" + roleid +
                ", permissionid=" + permissionid +
                '}';
    }
}
