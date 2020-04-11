package com.smss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjb（C）
 * describe
 */


@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String register() {
        return "你好，中国！！";

    }
}
