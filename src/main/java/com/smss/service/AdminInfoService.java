package com.smss.service;

import com.smss.mapper.AdminMapper;
import com.smss.model.*;
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
public class AdminInfoService {

    @Autowired
    private AdminMapper adminMapper;

    public UserInfo querUserByName(String username) {
        return adminMapper.querUserByName(username);
    }

    public UserInfo querUserById(int user_id) {
        return adminMapper.querUserById(user_id);
    }

    public void updateLogin(AdminBean adminBean) {
        adminMapper.updateAdminLogin(adminBean);
    }

    public void updateOpening(UserInfo userInfo) {
        adminMapper.updateOpening(userInfo);
    }

    public AdminBean querAdminByAdminName(String admin_name) {
        return adminMapper.querAdminByAdminName(admin_name);
    }

    public void reSetOpenning(String username) {
        adminMapper.reSetOpenning(username);
    }

    public List<AdviceBean> getAdvices() {
        return adminMapper.getAdvices();
    }

    public List<UpdatasBean> queryKsmsInfo() {
        return adminMapper.queryKsmsInfo();
    }

    public AdminBean querAdminByAdminNameAndPasswod(String admin_name, String admin_password) {
        return adminMapper.querAdminByAdminNameAndPasswod(admin_name, admin_password);
    }

    public List<UserInfo> queryAllUserInfo() {
        return adminMapper.queryAllUserInfo();
    }


    public List<CamiloBean> queryCamiloList(String type, String status) {
        return adminMapper.queryCamiloList(type, status);
    }

    public List<CamiloBean> queryCamiloAll() {
        return adminMapper.queryCamiloAll();
    }

    public CamiloBean queryOneCamilo(String type, String status) {
        return adminMapper.queryOneCamilo(type, status);
    }

    public void insertCamilolist(List<CamiloBean> list) {
        adminMapper.insertCamilolist(list);
    }

    public CamiloBean queryFileIsExist(String fileName) {
        return adminMapper.queryFileIsExist(fileName);
    }
}
