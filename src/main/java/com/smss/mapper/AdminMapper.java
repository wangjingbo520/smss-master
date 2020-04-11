package com.smss.mapper;

import com.smss.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wjb（C）
 * describe
 */

@Mapper
@Repository
public interface AdminMapper {

    public UserInfo querUserByName(String querUserIByName);

    public UserInfo querUserById(int user_id);

    public List<UserInfo> queryAllUserInfo();

    public void updateOpening(UserInfo userInfo);

    public AdminBean querAdminByAdminName(String admin_name);

    public AdminBean querAdminByAdminNameAndPasswod(String admin_name, String admin_password);

    public void updateAdminLogin(AdminBean adminBean);

    public List<AdviceBean> getAdvices();

    public void reSetOpenning(String username);

    public List<UpdatasBean> queryKsmsInfo();

    public List<CamiloBean> queryCamiloList(String type, String status);

    public List<CamiloBean> queryCamiloAll();

    public CamiloBean queryOneCamilo(String type, String status);

    public void insertCamilolist(List<CamiloBean> list);

    public CamiloBean queryFileIsExist(String fileName);
}
