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
public class AdviceBean {

    private String username;
    private String advice;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
