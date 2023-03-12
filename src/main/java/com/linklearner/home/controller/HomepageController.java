package com.linklearner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/api")
public class HomepageController {
    @RequestMapping("/homepage")
    public Map<String, Object> getHomepageData() {
        return null;
    }
}
