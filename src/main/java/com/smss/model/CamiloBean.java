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
public class CamiloBean {

    private String camilo;

    //    类型：1:一个月
//         2：两个月
//         3：三个月
    private int type;

    //是否正在使用是否正在使:
    //1：未使用
    //2：正在使用
    private int status;

    private String fileName;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCamilo() {
        return camilo;
    }

    public void setCamilo(String camilo) {
        this.camilo = camilo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
