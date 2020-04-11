package com.smss.model;

import com.smss.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wjb（C）
 * describe
 */

@Data
@AllArgsConstructor
public class AdminBean {

    private String admin_name;
    private String admin_password;
    private int id;
    private String admin_login_status;

    public AdminBean() {
        admin_login_status = Constants.TAG_LOGIN_NO;
    }

    public String getAdmin_login_status() {
        return admin_login_status;
    }

    public void setAdmin_login_status(String admin_login_status) {
        this.admin_login_status = admin_login_status;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AdminBean{" +
                "admin_name='" + admin_name + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", id=" + id +
                ", admin_login_status='" + admin_login_status + '\'' +
                '}';
    }
}
