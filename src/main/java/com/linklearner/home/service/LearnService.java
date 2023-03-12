package com.linklearner.home.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linklearner.home.entity.*;
import com.linklearner.home.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class LearnService {
    private LearnMapper learnMapper;
    private LearnTagRelationMapper learnTagRelationMapper;

    private LearnTagMapper learnTagMapper;

    private LearnDetailMapper learnDetailMapper;

    private LearnVideoMapper learnVideoMapper;

    public List<Map<String, Object>> findLearn(Learn param) {
        List<Learn> learnList = learnMapper.findLearn(param, Map.of("oid", "asc", "id", "desc"), null, null);
        return createLearn(learnList);
    }


    public List<Map<String, Object>> findFiveLikeDesc() {
        //    @Select("select * from learn order by `like` desc limit 5")
        List<Learn> learnList = learnMapper.findLearn(null, Map.of("`like`", "desc"), 0, 5);
        return createLearn(learnList);
    }

    public List<Map<String, Object>> findLearnByTag(Long tagId) {
        List<LearnTagRelation> targetLearnTagRelationList =
                learnTagRelationMapper.selectList(new QueryWrapper<LearnTagRelation>().eq("learn_tag_id", tagId));

        Set<Long> targetLearnIdList = new HashSet<>(targetLearnTagRelationList.stream().map(LearnTagRelation::getLearnId).toList());
        List<Learn> learnList = learnMapper.selectList(new QueryWrapper<Learn>().in("id", targetLearnIdList));
        return createLearn(learnList);

    }

    public List<Map<String, Object>> findLearnVideo(Long learnId) {
        List<LearnVideo> learnVideoList = learnVideoMapper.selectList(new QueryWrapper<LearnVideo>().eq("learn_id", learnId).orderByAsc("`index`"));
        return learnVideoList.stream().map(item -> Map.<String, Object>of(
                "videoId", item.getId(),
                "learnId", item.getLearnId(),
                "videoUrl", item.getVideoUrl(),
                "imageUrl", item.getImageUrl(),
                "title", item.getTitle())).toList();
    }

    public Map<String, Object> findLearnDetail(Long learnId, Long chapterId) {
        LearnDetail learnDetail = learnDetailMapper.selectOne(
                new QueryWrapper<LearnDetail>()
                        .eq("id", chapterId)
                        .eq("learn_id", learnId));
        return Map.of(
                "chapterId", learnDetail.getId(),
                "learnId", learnDetail.getLearnId(),
                "title", learnDetail.getTitle(),
                "content", learnDetail.getContent());
    }

    private List<Map<String, Object>> createLearn(List<Learn> learnList) {
        List<LearnTagRelation> learnTagRelationList =
                learnTagRelationMapper.findByLearnIds(learnList.stream().map(Learn::getId).toList());
        List<LearnTag> learnTagList;
        if (learnTagRelationList.size() != 0) {
            learnTagList =
                    learnTagMapper.findByIds(learnTagRelationList.stream().map(LearnTagRelation::getLearnTagId).toList());
        } else {
            learnTagList = Collections.emptyList();
        }

        List<LearnDetail> learnDetailList = learnDetailMapper.findByLearnIds(learnList.stream().map(Learn::getId).toList());
        // 组合learn、tag、detail
        List<Map<String, Object>> output = learnList.stream().map(learn -> {
            List<Map<String, Object>> tagList = learnTagRelationList.stream()
                    .filter(relation -> relation.getLearnId().equals(learn.getId()))
                    .map(relation -> {
                        List<LearnTag> tagInfoList = learnTagList.stream().filter(tag -> tag.getId().equals(relation.getLearnTagId())).toList();
                        if (tagInfoList.size() == 0) {
                            return Map.<String, Object>of("tagId", relation.getLearnTagId(), "tagName", "");
                        } else {
                            return Map.<String, Object>of("tagId", relation.getLearnTagId(), "tagName", tagInfoList.get(0).getName());
                        }
                    }).toList();
            List<Map<String, Object>> detailList = learnDetailList.stream()
                    .filter(detail -> detail.getLearnId().equals(learn.getId()))
                    .map(detail -> Map.<String, Object>of("chapterId", detail.getId(),
                            "title", detail.getTitle())).toList();

            return Map.<String, Object>of(
                    "id", learn.getId(),
                    "name", learn.getName(),
                    "description", learn.getDescription(),
                    "imageUrl", learn.getImageUrl(),
                    "like", learn.getLike() == null ? 0 : learn.getLike(),
                    "tagList", tagList,
                    "detailList", detailList);
        }).toList();

        return output;
    }

    @Autowired
    public void setLearnMapper(LearnMapper learnMapper) {
        this.learnMapper = learnMapper;
    }

    @Autowired
    public void setLearnTagRelationMapper(LearnTagRelationMapper learnTagRelationMapper) {
        this.learnTagRelationMapper = learnTagRelationMapper;
    }

    @Autowired
    public void setLearnTagMapper(LearnTagMapper learnTagMapper) {
        this.learnTagMapper = learnTagMapper;
    }

    @Autowired
    public void setLearnDetailMapper(LearnDetailMapper learnDetailMapper) {
        this.learnDetailMapper = learnDetailMapper;
    }

    @Autowired
    public void setLearnVideoMapper(LearnVideoMapper learnVideoMapper) {
        this.learnVideoMapper = learnVideoMapper;
    }
}
