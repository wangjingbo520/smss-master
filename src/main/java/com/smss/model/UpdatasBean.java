package com.smss.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wjb（C）
 * describe
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatasBean {

    private String username;
    private String uploadSucessSize;
    private String uploadFailedSize;
    private String times;
    private String remaining_size;

    public String getRemaining_size() {
        return remaining_size;
    }

    public void setRemaining_size(String remaining_size) {
        this.remaining_size = remaining_size;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUploadSucessSize() {
        return uploadSucessSize;
    }

    public void setUploadSucessSize(String uploadSucessSize) {
        this.uploadSucessSize = uploadSucessSize;
    }

    public String getUploadFailedSize() {
        return uploadFailedSize;
    }

    public void setUploadFailedSize(String uploadFailedSize) {
        this.uploadFailedSize = uploadFailedSize;
    }


    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

}
