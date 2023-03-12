package com.linklearner.home.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linklearner.home.entity.Article;
import com.linklearner.home.entity.ArticleTag;
import com.linklearner.home.entity.ArticleTagRelation;
import com.linklearner.home.mapper.ArticleMapper;
import com.linklearner.home.mapper.ArticleTagMapper;
import com.linklearner.home.mapper.ArticleTagRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    private ArticleMapper articleMapper;
    private ArticleTagRelationMapper articleTagRelationMapper;

    private ArticleTagMapper articleTagMapper;

    public List<Map<String, Object>> findArticle(QueryWrapper<Article> query) {
        List<Article> articleList = articleMapper.selectList(query);
        if (articleList.size() == 0) {
            return List.of();
        }
        List<ArticleTagRelation> articleTagRelationList = articleTagRelationMapper.selectList(
                new QueryWrapper<ArticleTagRelation>().in("article_id", articleList.stream().map(Article::getId).toArray()));
        List<ArticleTag> articleTagList = articleTagMapper.selectList(
                new QueryWrapper<ArticleTag>().in("id", articleTagRelationList.stream().map(ArticleTagRelation::getArticleTagId).toArray()));

        // 组合article、tag
        return articleList.stream().map(item -> {
            List<Map<String, Object>> tagList = articleTagRelationList.stream()
                    .filter(relation -> relation.getArticleId().equals(item.getId()))
                    .map(relation -> {
                        List<ArticleTag> tagInfoList = articleTagList.stream()
                                .filter(tag -> tag.getId().equals(relation.getArticleTagId())).toList();
                        if (tagInfoList.size() == 0) {
                            return Map.<String, Object>of("tagId", relation.getArticleTagId(), "tagName", "");
                        } else {
                            return Map.<String, Object>of("tagId", relation.getArticleTagId(), "tagName", tagInfoList.get(0).getName());
                        }
                    }).toList();
            return Map.of(
                    "id", item.getId(),
                    "area", item.getArea(),
                    "name", item.getName(),
                    "author", item.getAuthor() == null ? "" : item.getAuthor(),
                    "imageUrl", item.getImageUrl(),
                    "url", item.getUrl(),
                    "releaseTime", item.getReleaseTime() == null ? "" : item.getReleaseTime(),
                    "tagList", tagList);
        }).toList();

    }

    public List<Map<String, Object>> getAllTag() {
        return articleTagMapper.selectList(new QueryWrapper<>()).stream().map(item -> Map.<String, Object>of(
                "tagId", item.getId(),
                "name", item.getName()
        )).toList();
    }

    public List<Map<String, Object>> findArticleByTag(Long tagId, Long area) {
        var targetArticleTagRelationList = articleTagRelationMapper.selectList(new QueryWrapper<ArticleTagRelation>().eq("article_tag_id", tagId));
        var targetArticleIdList = new HashSet<>(targetArticleTagRelationList.stream().map(ArticleTagRelation::getArticleId).toList());
        return findArticle(new QueryWrapper<Article>().in("id", targetArticleIdList.toArray()).eq("area", area));
    }

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Autowired
    public void setArticleTagRelationMapper(ArticleTagRelationMapper articleTagRelationMapper) {
        this.articleTagRelationMapper = articleTagRelationMapper;
    }

    @Autowired
    public void setArticleTagMapper(ArticleTagMapper articleTagMapper) {
        this.articleTagMapper = articleTagMapper;
    }
}
