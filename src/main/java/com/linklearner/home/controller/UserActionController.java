package com.linklearner.home.controller;

import com.linklearner.home.service.UserActionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserActionController {
    private UserActionService userActionService;

    @PostMapping("/api/actions")
    @ResponseBody
    public Response addUserAction(@RequestBody Map<String, String> requestParams, HttpServletResponse response) {
        // 用户行为
        String sessionId = requestParams.get("sessionId");
        String logList = requestParams.get("logList");
        userActionService.addUserAction(sessionId, logList);
        return Response.success(response, "save user action successful");
    }

    @Autowired
    public void setUserActionService(UserActionService userActionService) {
        this.userActionService = userActionService;
    }
}
