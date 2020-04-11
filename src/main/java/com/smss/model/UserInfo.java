package com.smss.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wjb（C）
 * describe  用户信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private int user_id;
    private String username;
    private String password;
    private String login_status;

    private String register_date;

    private String start_date;

    private String end_date;

    private int remaining_day;

    private String device_id;

    //卡密
    private String camilo;


    public String getCamilo() {
        return camilo;
    }

    public void setCamilo(String camilo) {
        this.camilo = camilo;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getRemaining_day() {
        return remaining_day;
    }

    public void setRemaining_day(int remaining_day) {
        this.remaining_day = remaining_day;
    }

    private int opening;

    public int getOpening() {
        return opening;
    }

    public void setOpening(int opening) {
        this.opening = opening;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }


    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", login_status='" + login_status + '\'' +
                ", register_date='" + register_date + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", remaining_day=" + remaining_day +
                ", opening=" + opening +
                '}';
    }
}
