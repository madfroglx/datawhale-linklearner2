package com.linklearner.home.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linklearner.home.entity.Learn;
import com.linklearner.home.entity.LearnTag;
import com.linklearner.home.mapper.LearnTagMapper;
import com.linklearner.home.service.LearnService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class LearnController {
    private LearnService learnService;
    private LearnTagMapper learnTagMapper;

    @GetMapping(value = "/api/learn/tag")
    @ResponseBody
    public Response getLearnByTag(@RequestParam(required = false) String tag, HttpServletResponse response) {
        List<LearnTag> learnTags = learnTagMapper.selectList(new QueryWrapper<>());
        List<Map<String, Object>> data = learnTags.stream().map(item ->
                Map.<String, Object>of("tagId", item.getId(), "name", item.getName())).toList();
        return Response.success(response, data);
    }

    @GetMapping(value = "/api/learn")
    @ResponseBody
    public Response getLearn(@RequestParam(required = false) Long learnId, HttpServletResponse response) {
        List<Map<String, Object>> learns;
        if (learnId == null) {
            learns = learnService.findLearn(new Learn());
        } else {
            learns = learnService.findLearn(new Learn(learnId));
        }
        // 获取首页推荐的学习列表
        return Response.success(response, learns);
    }

    @GetMapping("/api/learn/getByTag")
    @ResponseBody
    public Response getLearnByTag(@RequestParam Long tagId, HttpServletResponse response) {
        List<Map<String, Object>> data = learnService.findLearnByTag(tagId);
        return Response.success(response, data);
    }

    @GetMapping("/api/learn/video")
    @ResponseBody
    public Response getLearnVideo(@RequestParam Long learnId, HttpServletResponse response) {
        List<Map<String, Object>> data = learnService.findLearnVideo(learnId);
        return Response.success(response, data);
    }

    @GetMapping("/api/learn/detail")
    @ResponseBody
    public Response getLearnDetail(@RequestParam Long learnId, @RequestParam Long chapterId, HttpServletResponse response) {
        Map<String, Object> data = learnService.findLearnDetail(learnId, chapterId);
        return Response.success(response, data);
    }

    @Autowired
    public void setLearnTagMapper(LearnTagMapper learnTagMapper) {
        this.learnTagMapper = learnTagMapper;
    }

    @Autowired
    public void setLearnService(LearnService learnService) {
        this.learnService = learnService;
    }
}
