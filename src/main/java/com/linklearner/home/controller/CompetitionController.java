package com.linklearner.home.controller;

import com.linklearner.home.service.CompetitionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

@Controller
public class CompetitionController {
    private CompetitionService competitionService;


    @GetMapping("/api/competition")
    @ResponseBody
    public Response competition(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                Integer seriesTag, Integer categoryTag, Integer fieldTag, Integer difficultyTag,
                                HttpServletResponse response) {
        Map<String, Object> data = new java.util.HashMap<>(Map.of(
                "competitionTagList", Collections.emptyList(),
                "competitionList", Collections.emptyList(),
                "total", 0,
                "totalPages", 0));
        // 获取competitionTagList
        var competitionTagList = competitionService.getAllCompetitionTag();
        if (competitionTagList != null) {
            data.put("competitionTagList", competitionTagList);
        }
        // 获取competitionList
        var competitionList = competitionService.findCompetitionList(page, pageSize, seriesTag, categoryTag, fieldTag, difficultyTag);
        if (competitionList != null) {
            data.put("competitionList", competitionList.getRecords());
            data.put("total", competitionList.getTotal());
            data.put("totalPages", (competitionList.getTotal() / pageSize) + 1);
        }
        return Response.success(response, data);
    }

    @Autowired
    public void setCompetitionService(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
}
