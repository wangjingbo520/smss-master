package com.smss.controller;

import com.smss.model.CamiloBean;
import com.smss.model.UpdatasBean;
import com.smss.model.UserInfo;
import com.smss.model.VersionApp;
import com.smss.net.RetResponse;
import com.smss.net.RetResult;
import com.smss.service.UserInfoService;
import com.smss.utils.Constants;
import com.smss.utils.DateUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

import static com.smss.utils.Constants.USEING_CAMIO;

/**
 * @author wjb（C）
 * describe
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RetResult<UserInfo> register(@RequestParam(value = "username", required = true) String username,
                                        @RequestParam(value = "password", required = true) String password,
                                        @RequestParam(value = "device_id", required = true) String device_id) {
        try {
            UserInfo userInfo = userInfoService.querUserByName(username);
            if (userInfo == null) {
                userInfo = new UserInfo();
                userInfo.setUsername(username);
                userInfo.setPassword(password);
                userInfo.setDevice_id(device_id);
                userInfo.setStart_date("0-0-0");
                userInfo.setEnd_date("0-0-0");
                userInfo.setLogin_status(Constants.TAG_LOGIN_NO);
                userInfo.setRegister_date(DateUtils.getCurrentDate());
                userInfoService.register(userInfo);
                return RetResponse.makeOKRsp(userInfo, "注册成功");
            } else {
                return RetResponse.makeNullDataRsp("该用户已存在，请重新注册");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }


    /**
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/login")
    public RetResult<UserInfo> login(@RequestParam("username") String username, @RequestParam("password") String password
            , @RequestParam("device_id") String device_id) {
        try {
            UserInfo userInfo = userInfoService.querUserByName(username);
            if (userInfo != null) {
                UserInfo userInfo1 = userInfoService.querUserByNameAndPassword(username, password);
                if (userInfo1 != null) {
                    //简单实现单点登录
                    String deviceId = userInfo1.getDevice_id();
                    if (TextUtils.isEmpty(deviceId)) {
                        return RetResponse.makeNullDataRsp("数据库不存在该手机标识码，请重新启动登录！");
                    }

                    userInfo1.setLogin_status(Constants.TAG_LOGIN_YES);
                    String message;
                    if (!device_id.equals(deviceId)) {
                        message = "您的账号在其他设备上登录过，已经把它挤下线！";
                    } else {
                        message = "登录成功";
                    }
                    userInfoService.updateLogin(userInfo1);
                    return RetResponse.makeOKRsp(userInfo1, message);
                } else {
                    return RetResponse.makeNullDataRsp("密码错误");
                }
            } else {
                return RetResponse.makeNullDataRsp("用户名不存在");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }

    /**
     * 挤下线重新设置 device_id
     *
     * @param username
     * @return
     */
    @PostMapping(value = "/reLogin")
    public RetResult<UserInfo> reLogin(@RequestParam("username") String username, @RequestParam("device_id") String device_id) {
        try {
            UserInfo userInfo = userInfoService.querUserByName(username);
            if (userInfo != null) {
                userInfo.setDevice_id(device_id);
                userInfo.setLogin_status(Constants.TAG_LOGIN_YES);
                userInfoService.reLogin(userInfo);
                return RetResponse.makeOKRsp(userInfo, "登录成功");
            } else {
                return RetResponse.makeNullDataRsp("用户名不存在");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/updatePassWord", method = RequestMethod.POST)
    public RetResult<UserInfo> updatePassWord(@RequestParam(value = "username", required = true) String username,
                                              @RequestParam(value = "password", required = true) String password) {
        try {
            UserInfo userInfo = userInfoService.querUserByName(username);
            if (userInfo != null) {
                userInfo.setPassword(password);
                userInfoService.updatePassWord(userInfo);
                return RetResponse.makeOKRsp(userInfo, "恭喜您，修改密码成功");
            } else {
                return RetResponse.makeNullDataRsp("该用户不存在");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/exitLogin", method = RequestMethod.POST)
    public RetResult<UserInfo> exitLogin(@RequestParam(value = "username", required = true) String username,
                                         @RequestParam(value = "password", required = true) String password) {
        try {
            UserInfo userInfo = userInfoService.querUserByName(username);
            if (userInfo != null) {
                userInfo.setLogin_status(Constants.TAG_LOGIN_NO);
                userInfoService.exitLogin(userInfo);
                return RetResponse.makeOKRsp(userInfo, "成功退出");
            } else {
                return RetResponse.makeNullDataRsp("该用户不存在");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }

    /**
     * @param username
     * @return
     */
    @RequestMapping(value = "/querUserIByName", method = RequestMethod.POST)
    public RetResult<UserInfo> querUserIByName(@RequestParam(value = "username", required = true) String username) {
        try {
            UserInfo userInfo = userInfoService.querUserByName(username);
            if (userInfo != null) {
                return RetResponse.makeOKRsp(userInfo, "用户信息");
            } else {
                return RetResponse.makeNullDataRsp("该用户不存在");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }

    /**
     * @param versionCode
     * @param versionName
     * @return
     */
    @RequestMapping(value = "/checkAppVersion", method = RequestMethod.POST)
    public RetResult<VersionApp> checkAppVersion(@RequestParam(value = "versionCode", required = true) String versionCode,
                                                 @RequestParam(value = "versionName", required = true) String versionName) {
        try {
            VersionApp versionApp = userInfoService.checkAppVersion();
            if (versionApp != null) {
                return RetResponse.makeOKRsp(versionApp, "版本信息");
            } else {
                return RetResponse.makeNullDataRsp("不存在版本......");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }

    /**
     * @param username
     * @param advice
     * @return
     */
    @RequestMapping(value = "/advice", method = RequestMethod.POST)
    public RetResult<UserInfo> advices(@RequestParam(value = "username", required = true) String username,
                                       @RequestParam(value = "advice", required = true) String advice) {
        try {
            UserInfo userInfo = userInfoService.querUserByName(username);
            if (userInfo != null) {
                userInfoService.advices(username, advice);
                return RetResponse.makeOKRsp(userInfo, "恭喜您，提交成功");
            } else {
                return RetResponse.makeNullDataRsp("用户不存在，无法提交建议！！！");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }

    }


    /**
     * 1069通道上传
     *
     * @param username
     * @param username
     * @return
     */
    @RequestMapping(value = "/upLoadDatas", method = RequestMethod.POST)
    public RetResult<String> upLoadDatas(@RequestParam(value = "username") String username,
                                         @RequestParam(value = "uploadSucessSize") String uploadSucessSize,
                                         @RequestParam(value = "uploadFailedSize") String uploadFailedSize,
                                         @RequestParam(value = "remaining_size") String remaining_size) {
        try {
            UserInfo userInfo = userInfoService.querUserByName(username);
            if (userInfo != null) {
                UpdatasBean updatasBean = new UpdatasBean();
                updatasBean.setUsername(username);
                updatasBean.setTimes(DateUtils.getCurrentDate());
                updatasBean.setUploadFailedSize(uploadFailedSize);
                updatasBean.setUploadSucessSize(uploadSucessSize);
                updatasBean.setRemaining_size(remaining_size);
                userInfoService.upLoadDatas(updatasBean);
                return RetResponse.makeOKRsp("sucess", "上传成功");
            } else {
                return RetResponse.makeNullDataRsp("1069管理员用户不存在！！");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }


    /**
     * @param camilo
     * @param username
     * @return
     */
    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public RetResult<UserInfo> charge(@RequestParam(value = "camilo") String camilo,
                                      @RequestParam(value = "username") String username) {
        try {
            UserInfo userInfo = userInfoService.querUserByName(username);
            if (userInfo != null) {
                //1：未使用  2：正在使用
                CamiloBean camiloBean = userInfoService.querCamilo(camilo);
                if (camiloBean != null) {
                    if (camiloBean.getStatus() == USEING_CAMIO) {
                        //正在使用
                        return RetResponse.makeNullDataRsp("该卡密已经在使用中，请重新操作");
                    }

                    int type = camiloBean.getType();
                    if (type == Constants.DAY_USE_ONE) {
                        userInfo.setRemaining_day(30);
                    } else if (type == Constants.DAY_USE_TWO) {
                        userInfo.setRemaining_day(60);
                    } else if (type == Constants.DAY_USE_THREE) {
                        userInfo.setRemaining_day(90);
                    }

                    userInfo.setCamilo(camiloBean.getCamilo());
                    userInfo.setOpening(Constants.IS_OPENING);
                    userInfoService.updateUserInfoByCamilo(userInfo);

                    //更新卡密的状态，为正在使用
                    camiloBean.setStatus(1);
                    userInfoService.updataCamiloStatus(camiloBean);
                    return RetResponse.makeOKRsp(userInfo, "您已成功开通短信会员");
                } else {
                    return RetResponse.makeNullDataRsp("卡密错误，请重新输入");
                }
            } else {
                return RetResponse.makeNullDataRsp("该用户不存在,请重新登录开通vip!!!");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }


    @RequestMapping("/downloadApk")
    public String downloadFile(HttpServletResponse response) throws UnsupportedEncodingException {
        VersionApp latelyVersion = userInfoService.queryLatelyVersion();
        if (latelyVersion == null) {
            return "apk文件不存在";
        }
        String fileName = latelyVersion.getAppName(); //下载的文件名
        //设置文件路径
        // String realPath = "F:\\myresource\\code\\SMS\\app\\release";
        String realPath = "C:\\smss\\apk";
        File file = new File(realPath, fileName);
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Content-Length", "" + file.length());
            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the song successfully!");
            } catch (Exception e) {
                System.out.println("Download the song failed!");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return "error";
    }

}

