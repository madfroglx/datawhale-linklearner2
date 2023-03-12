package com.linklearner.home.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linklearner.home.entity.Activity;
import com.linklearner.home.mapper.ActivityMapper;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ActivityService {
    @Autowired
    ActivityMapper activityMapper;

    public List<Map<String, String>> findAvailableActivity() {
        List<Activity> activities = activityMapper.selectList(new QueryWrapper<Activity>().eq("registration_end_time", new Date()));
        List<Map<String, String>> result = new ArrayList<>();
        activities.stream().map(activity -> Map.of(
                "name", activity.getName(),
                "description", activity.getDescription(),
                "link", activity.getRegistrationLink(),
                "endTime", DateFormatUtils.format(activity.getRegistrationEndTime(), "yyyy-MM-dd"))
        ).forEach(result::add);
        return result;
    }
}
