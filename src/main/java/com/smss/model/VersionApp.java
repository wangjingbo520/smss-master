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
public class VersionApp {


    private int versionCode;
    private String versionName;
    private String appName;
    private String versionDescribed;

    private String downUrl;


    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersionDescribed() {
        return versionDescribed;
    }

    public void setVersionDescribed(String versionDescribed) {
        this.versionDescribed = versionDescribed;
    }
}
