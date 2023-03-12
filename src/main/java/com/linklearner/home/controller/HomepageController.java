package com.linklearner.home.controller;

import com.linklearner.home.service.ActivityService;
import com.linklearner.home.service.BannerService;
import com.linklearner.home.service.LearnService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class HomepageController {

    private BannerService bannerService;
    private ActivityService activityService;
    private LearnService learnService;

    @GetMapping(value = "/api/homepage")
    @ResponseBody
    public Response getHomepageData(HttpServletResponse response) {

        // 获取banner
        List<Map<String, String>> bannerList = bannerService.findFiveAvailableBanner();
        // 获取当前可用的活动列表
        List<Map<String, String>> activityList = activityService.findAvailableActivity();

        List<Map<String, Object>> learnList = learnService.findFiveLikeDesc();
        // 获取首页推荐的学习列表

        return Response.success(response,
                Map.of("banner", bannerList,
                        "activity", activityList,
                        "learn", learnList));
    }

    @Autowired
    public void setBannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Autowired
    public void setLearnService(LearnService learnService) {
        this.learnService = learnService;
    }
}
