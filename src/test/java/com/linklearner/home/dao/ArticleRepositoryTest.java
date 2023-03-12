package com.linklearner.home.dao;

import com.linklearner.DemoApplication;
import com.linklearner.home.entity.Article;
import com.linklearner.home.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void testLoadById() {
        Article article = articleMapper.findById(1L);
        System.out.println(article);
    }

}