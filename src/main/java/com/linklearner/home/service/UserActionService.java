package com.linklearner.home.service;

import com.linklearner.home.entity.UserAction;
import com.linklearner.home.mapper.UserActionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActionService {
    private UserActionMapper userActionMapper;

    public void addUserAction(String sessionId, String logList) {
        UserAction userAction = new UserAction();
        userAction.setSessionId(sessionId);
        userAction.setLogList(logList);
        userActionMapper.insert(userAction);
    }

    @Autowired
    public void setUserActionMapper(UserActionMapper userActionMapper) {
        this.userActionMapper = userActionMapper;
    }
}
