package com.smss.service;

import com.smss.mapper.UserInfoMapper;
import com.smss.model.CamiloBean;
import com.smss.model.UpdatasBean;
import com.smss.model.UserInfo;
import com.smss.model.VersionApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wjb（C）
 * describe
 */

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserInfoService {

    @Autowired
    private UserInfoMapper userMapper;


    public List<UserInfo> querAllUserInfo() {
        return userMapper.querAllUserInfo();
    }


    public UserInfo login(UserInfo userInfo) {
        return userMapper.login(userInfo);
    }

    public int register(UserInfo userInfo) {
        return userMapper.register(userInfo);
    }

    public void updatePassWord(UserInfo userInfo) {
        userMapper.updatePassWord(userInfo);
    }

    public UserInfo querUserByName(String username) {
        return userMapper.querUserByName(username);
    }

    public CamiloBean querCamilo(String camilo) {
        return userMapper.querCamilo(camilo);
    }

    public void updateUserInfoByCamilo(UserInfo userInfo) {
        userMapper.updateUserInfoByCamilo(userInfo);
    }

    public void reLogin(UserInfo userInfo) {
        userMapper.reLogin(userInfo);
    }

    public VersionApp checkAppVersion() {
        return userMapper.checkAppVersion();
    }

    public void advices(String username, String advice) {
        userMapper.advices(username, advice);
    }

    public UserInfo querUserByNameAndPassword(String username, String password) {
        return userMapper.querUserByNameAndPassword(username, password);
    }

    public void exitLogin(UserInfo userInfo) {
        userMapper.exitLogin(userInfo);
    }

    public void updateLogin(UserInfo userInfo) {
        userMapper.updateLogin(userInfo);
    }


    public void updateOpening(UserInfo userInfo) {
        userMapper.updateOpening(userInfo);
    }

    public void upLoadDatas(UpdatasBean updatasBean) {
        userMapper.upLoadDatas(updatasBean);
    }


    public void updataCamiloStatus(CamiloBean camiloBean) {
        userMapper.updataCamiloStatus(camiloBean);
    }

    public VersionApp queryLatelyVersion() {
        return userMapper.queryLatelyVersion();
    }
}
