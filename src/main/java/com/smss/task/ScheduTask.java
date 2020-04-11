package com.smss.task;

import com.smss.model.UserInfo;
import com.smss.service.AdminInfoService;
import com.smss.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wjb（C）
 * describe 定时任务，自减操作
 */

@Component
public class ScheduTask {

    @Autowired
    private AdminInfoService adminInfoService;

    //凌晨一点
    @Scheduled(cron = "0 0 1 * * ?")
    public void reduceOneDay() {
        List<UserInfo> userInfos = adminInfoService.queryAllUserInfo();
        if (userInfos == null) {
            return;
        }
        for (UserInfo info : userInfos) {
            if (info.getOpening() == Constants.IS_OPENING) {
                int remainDays = info.getRemaining_day();
                if (remainDays <= 0) {
                    info.setStart_date("0-0-0");
                    info.setEnd_date("0-0-0");
                    info.setRemaining_day(0);
                    info.setOpening(Constants.NO_OPENING);
                } else {
                    info.setRemaining_day(remainDays - 1);
                }
                adminInfoService.updateOpening(info);
            }
        }
    }
}
