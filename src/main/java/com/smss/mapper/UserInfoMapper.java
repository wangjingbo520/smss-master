package com.smss.mapper;

import com.smss.model.CamiloBean;
import com.smss.model.UpdatasBean;
import com.smss.model.UserInfo;
import com.smss.model.VersionApp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wjb（C）
 * describe
 */

@Mapper
@Repository
public interface UserInfoMapper {
    public List<UserInfo> querAllUserInfo();

    public UserInfo login(UserInfo userInfo);

    public void updateLogin(UserInfo userInfo);

    public int register(UserInfo userInfo);

    public void updatePassWord(UserInfo userInfo);

    public UserInfo querUserByName(String querUserIByName);

    public void reLogin(UserInfo userInfo);

    public UserInfo querUserByNameAndPassword(String username, String password);

    public boolean save(UserInfo userInfo);

    public boolean delete(int id);

    public List<UserInfo> findAll();

    public void exitLogin(UserInfo userInfo);

    public void updateOpening(UserInfo userInfo);

    public VersionApp checkAppVersion();

    public void advices(String username, String advice);


    public void upLoadDatas(UpdatasBean updatasBean);

    public CamiloBean querCamilo(String camilo);

    public void updateUserInfoByCamilo(UserInfo userInfo);

    public void updataCamiloStatus(CamiloBean camiloBean);

    public VersionApp queryLatelyVersion();
}