package com.linklearner.home.controller;

import com.linklearner.home.service.KnowledgeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KnowledgeController {
    private KnowledgeService knowledgeService;

    @GetMapping("/api/knowledge/list")
    @ResponseBody
    public Response knowledgeList(HttpServletResponse response) {
        return Response.success(response, knowledgeService.findAvailableKnowledge());
    }

    @GetMapping("/api/knowledge/mind")
    @ResponseBody
    public Response knowledgeMind(HttpServletResponse response) {
        return Response.success(response, knowledgeService.findAvailableKnowledgeMind().get(0));
    }

    @Autowired
    public void setKnowledgeService(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }
}
