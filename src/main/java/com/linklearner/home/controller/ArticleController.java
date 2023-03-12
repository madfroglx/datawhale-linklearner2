package com.linklearner.home.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linklearner.home.entity.Article;
import com.linklearner.home.service.ArticleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

@Controller
public class ArticleController {

    private ArticleService articleService;

    @RequestMapping(value = "/api/article", method = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Response article(@RequestParam(required = false) Long articleId, @RequestParam(required = false) Integer area, HttpServletResponse response) {
        if (area == null) {
            return Response.success(response, Collections.emptyList());
        } else if (articleId == null) {
            return Response.success(response, articleService.findArticle(new QueryWrapper<Article>().eq("area", area)));
        } else {
            return Response.success(response, articleService.findArticle(
                    new QueryWrapper<Article>()
                            .eq("id", articleId)
                            .eq("area", area)));
        }
    }

    @GetMapping("/api/article/tag")
    @ResponseBody
    public Response getAllTag(HttpServletResponse response) {
        return Response.success(response, articleService.getAllTag());
    }

    @GetMapping("/api/article/getByTag")
    @ResponseBody
    public Response getByTag(@RequestParam Long tagId, Long area, HttpServletResponse response) {
        return Response.success(response, articleService.findArticleByTag(tagId, area));
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
