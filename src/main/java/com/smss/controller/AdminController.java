package com.smss.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smss.model.*;
import com.smss.net.RetResponse;
import com.smss.net.RetResult;
import com.smss.service.AdminInfoService;
import com.smss.utils.Constants;
import com.smss.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wjb（C）
 * describe
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminInfoService adminInfoService;


    /**
     * @param admin_name
     * @param admin_password
     * @return
     */
    @PostMapping(value = "/login")
    public RetResult<AdminBean> login(@RequestParam("admin_name") String admin_name, @RequestParam("admin_password") String admin_password) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                AdminBean adminBean1 = adminInfoService.querAdminByAdminNameAndPasswod(admin_name, admin_password);
                if (adminBean1 != null) {
                    adminBean1.setAdmin_login_status(Constants.TAG_LOGIN_YES);
                    adminInfoService.updateLogin(adminBean1);
                    return RetResponse.makeOKRsp(adminBean1, "管理员登录成功");
                } else {
                    return RetResponse.makeNullDataRsp("密码错误");
                }
            } else {
                return RetResponse.makeNullDataRsp("不存在该管理员");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }

    }


    /**
     * @param username
     * @return
     */
    @RequestMapping(value = "/querUserByName", method = RequestMethod.POST)
    public RetResult<UserInfo> querUserByName(@RequestParam(value = "username", required = true) String username) {
        try {
            UserInfo userInfo = adminInfoService.querUserByName(username);
            if (userInfo != null) {
                return RetResponse.makeOKRsp(userInfo, "ok");
            } else {
                return RetResponse.makeNullDataRsp("该用户不存在");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }

    }


    /**
     * 所有会员
     *
     * @param admin_name
     * @return
     */
    @RequestMapping(value = "/querAllUserInfo", method = RequestMethod.POST)
    public RetResult<List<UserInfo>> querAllUser(@RequestParam(value = "admin_name", required = true) String admin_name) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                List<UserInfo> userInfos = adminInfoService.queryAllUserInfo();
                if (userInfos != null) {
                    return RetResponse.makeOKRsp(userInfos, "ok");
                } else {
                    return RetResponse.makeNullDataRsp("暂时没有用户哟..");
                }
            } else {
                return RetResponse.makeNullDataRsp("该管理员不存在");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }

    }


    /**
     * 管理者控制(此接口已废弃)
     *
     * @param username
     * @param start_date
     * @param end_date
     * @return
     */
    @RequestMapping(value = "/openingByName", method = RequestMethod.POST)
    public RetResult<UserInfo> openingByName(@RequestParam(value = "admin_name", required = true) String admin_name,
                                             @RequestParam(value = "username", required = true) String username,
                                             @RequestParam(value = "start_date", required = true) String start_date,
                                             @RequestParam(value = "end_date", required = true) String end_date) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                UserInfo userInfo = adminInfoService.querUserByName(username);
                if (userInfo != null) {
                    if (userInfo.getOpening() == Constants.IS_OPENING) {
                        //已开通
                        RetResponse.makeNullDataRsp("该用户已开通会员，剩余" + userInfo.getRemaining_day() + "天的使用时间");
                    } else if (userInfo.getOpening() == Constants.NO_OPENING) {
                        userInfo.setStart_date(start_date);
                        userInfo.setEnd_date(end_date);
                        userInfo.setOpening(Constants.IS_OPENING);
                        adminInfoService.updateOpening(userInfo);
                        return RetResponse.makeOKRsp(userInfo, "您已成功开通短信会员");
                    }
                    return RetResponse.makeNullDataRsp("数据发生了异常，请重新操作");
                } else {
                    return RetResponse.makeNullDataRsp("管理员注意，该用户不存在");
                }
            } else {
                return RetResponse.makeNullDataRsp("不存在该管理员！！！");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }

    }


    /**
     * 管理者控制(ID)   已废弃
     *
     * @param admin_name
     * @param user_id
     * @param start_date
     * @param end_date
     * @return
     */
    @RequestMapping(value = "/openingById", method = RequestMethod.POST)
    public RetResult<UserInfo> openingById(@RequestParam(value = "admin_name", required = true) String admin_name,
                                           @RequestParam(value = "user_id", required = true) String user_id,
                                           @RequestParam(value = "start_date", required = true) String start_date,
                                           @RequestParam(value = "end_date", required = true) String end_date) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                UserInfo userInfo = adminInfoService.querUserById(Integer.parseInt(user_id));
                if (userInfo != null) {
                    if (userInfo.getOpening() == Constants.IS_OPENING) {
                        //已开通了
                        int remaining_day = userInfo.getRemaining_day();
                        return RetResponse.makeNullDataRsp("该用户已开通会员，剩余" + remaining_day + "天的使用时间,请使用完再续费，谢谢！或者先重置用户，再设置！");
                    } else if (userInfo.getOpening() == Constants.NO_OPENING) {
                        //未开通
                        userInfo.setStart_date(start_date);
                        userInfo.setEnd_date(end_date);
                        int userable_days = DateUtils.caculateTotalTime(end_date, start_date);
                        System.out.println("====" + userable_days);
                        userInfo.setRemaining_day(userable_days);
                        userInfo.setOpening(Constants.IS_OPENING);
                        adminInfoService.updateOpening(userInfo);
                        return RetResponse.makeOKRsp(userInfo, "您已成功开通短信会员");
                    } else {
                        return RetResponse.makeNullDataRsp("对不起，您的会员状态有异常");
                    }
                } else {
                    return RetResponse.makeNullDataRsp("管理员注意，该用户不存在！请通知他先注册.....");
                }
            } else {
                return RetResponse.makeNullDataRsp("不存在该管理员！！！");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }


    /**
     * @param admin_name
     * @param admin_password
     * @return
     */
    @RequestMapping(value = "/adminExitLogin", method = RequestMethod.POST)
    public RetResult<AdminBean> exitLogin(@RequestParam(value = "admin_name", required = true) String admin_name,
                                          @RequestParam(value = "admin_password", required = true) String admin_password) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                adminBean.setAdmin_login_status(Constants.TAG_LOGIN_NO);
                adminInfoService.updateLogin(adminBean);
                return RetResponse.makeOKRsp(adminBean, "管理员成功退出");
            } else {
                return RetResponse.makeNullDataRsp("该用户不存在");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }

    }

    @RequestMapping(value = "/getAdvices", method = RequestMethod.POST)
    public RetResult<List<AdviceBean>> getAdvices(@RequestParam(value = "admin_name", required = true) String admin_name,
                                                  @RequestParam(value = "admin_password", required = true) String admin_password) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                List<AdviceBean> advices = adminInfoService.getAdvices();
                return RetResponse.makeOKRsp(advices, "查询成功");
            } else {
                return RetResponse.makeNullDataRsp("该用户不存在");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }

    @RequestMapping(value = "/reSetOpenning", method = RequestMethod.POST)
    public RetResult<UserInfo> reSetOpenning(@RequestParam(value = "username", required = true) String username,
                                             @RequestParam(value = "admin_name", required = true) String admin_name) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                UserInfo userInfo = adminInfoService.querUserByName(username);
                if (userInfo != null) {
                    int opening = userInfo.getOpening();
                    if (opening == Constants.IS_OPENING) {
                        adminInfoService.reSetOpenning(username);
                        userInfo.setOpening(Constants.NO_OPENING);
                        userInfo.setStart_date("0-0-0");
                        userInfo.setEnd_date("0-0-0");
                        return RetResponse.makeOKRsp(userInfo, "重新设置成功");
                    } else {
                        return RetResponse.makeNullDataRsp("该会员不是会员，无须重置");
                    }
                } else {
                    return RetResponse.makeNullDataRsp("管理员注意，该用户不存在");
                }
            } else {
                return RetResponse.makeNullDataRsp("该管理员不存在！！！");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }

    @RequestMapping(value = "/queryKsmsInfo", method = RequestMethod.POST)
    public RetResult<List<UpdatasBean>> queryKsmsInfo(@RequestParam(value = "admin_name", required = true) String admin_name) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                List<UpdatasBean> updatasBeans = adminInfoService.queryKsmsInfo();
                return RetResponse.makeOKRsp(updatasBeans, "查询成功");
            } else {
                return RetResponse.makeNullDataRsp("暂时还没有数据");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }

    }


    @RequestMapping(value = "/queryCamiloDate", method = RequestMethod.POST)
    public RetResult<List<CamiloBean>> queryCamiloList(@RequestParam(value = "admin_name", required = true) String admin_name,
                                                       @RequestParam(value = "type", required = true) String type,
                                                       @RequestParam(value = "status", required = true) String status) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                //全部
                if (type.equals("0")) {
                    return RetResponse.makeOKRsp(adminInfoService.queryCamiloAll(), "查询成功");
                } else {
                    return RetResponse.makeOKRsp(adminInfoService.queryCamiloList(type, status), "查询成功");
                }
            } else {
                return RetResponse.makeNullDataRsp("没有卡了");
            }
        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }
    }


    @RequestMapping(value = "/queryOneCamilo", method = RequestMethod.POST)
    public RetResult<CamiloBean> queryOneCamilo(@RequestParam(value = "admin_name", required = true) String admin_name,
                                                @RequestParam(value = "type", required = true) String type,
                                                @RequestParam(value = "status", required = true) String status) {
        try {
            AdminBean adminBean = adminInfoService.querAdminByAdminName(admin_name);
            if (adminBean != null) {
                //全部
                CamiloBean camiloBean = adminInfoService.queryOneCamilo(type, status);
                if (camiloBean != null) {
                    return RetResponse.makeOKRsp(camiloBean, "查询一条数据成功");
                } else {
                    return RetResponse.makeNullDataRsp("没有卡了");
                }
            } else {
                return RetResponse.makeNullDataRsp("没有卡了");
            }

        } catch (Exception ex) {
            return RetResponse.makeNullDataRsp("error: " + ex.getMessage());
        }

    }


    @ResponseBody
    @RequestMapping(value = "/uploadJsonData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String uploadJsonData(@RequestBody JSONArray jsonParam) {
        try {
            List<CamiloBean> camiloBeans = JSON.parseArray(jsonParam.toJSONString(), CamiloBean.class);
            if (camiloBeans != null) {
                if (camiloBeans.size() > 0) {
                    String fileName = camiloBeans.get(0).getFileName();
                    if (fileName == null) {
                        return "您上传的文件中，文件名不存在！";
                    }

                    CamiloBean camiloBean = adminInfoService.queryFileIsExist(fileName);
                    if (camiloBean == null) {
                        //不存在，直接插入
                        adminInfoService.insertCamilolist(camiloBeans);
                        return "卡密上传成功！！！";
                    } else {
                        return "该文件您已经上传过啦";
                    }

                } else {
                    return "您上传的文件数据有问题，请重新上传！！";
                }
            } else {
                return "该文件您已经上传过啦！！";

            }
        } catch (Exception ex) {
            return "error: " + ex.getMessage();
        }
    }


}
