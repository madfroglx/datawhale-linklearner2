package com.linklearner.home.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linklearner.home.entity.Competition;
import com.linklearner.home.entity.CompetitionTag;
import com.linklearner.home.mapper.CompetitionMapper;
import com.linklearner.home.mapper.CompetitionTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CompetitionService {
    private CompetitionMapper competitionMapper;
    private CompetitionTagMapper competitionTagMapper;

    public List<Map<String, Object>> getAllCompetitionTag() {
        List<CompetitionTag> tagList = competitionTagMapper.selectList(new QueryWrapper<>());
        return tagList.stream().map(tag -> Map.<String, Object>of("id", tag.getId(),
                "name", tag.getName(),
                "type", tag.getType(), "typeName",
                Objects.requireNonNull(CompetitionTag.COMPETITION_TAG_TYPE.getNameByValue(tag.getType())))).toList();
    }

    public Page<Competition> findCompetitionList(int page, int pageSize, Integer seriesTag, Integer categoryTag, Integer fieldTag, Integer difficultyTag) {
        int start = (page - 1) * pageSize;
        QueryWrapper<Competition> queryWrapper = new QueryWrapper<>();
        if (seriesTag != null) {
            queryWrapper.eq("series_tag", seriesTag);
        }
        if (categoryTag != null) {
            queryWrapper.eq("category_tag", categoryTag);
        }
        if (fieldTag != null) {
            queryWrapper.eq("field_tag", fieldTag);
        }
        if (difficultyTag != null) {
            queryWrapper.eq("difficulty_tag", difficultyTag);
        }
        Long count = competitionMapper.selectCount(queryWrapper);
        if (start >= count) {
            start = 0;
        }
//        List<Competition> competitionList = competitionMapper.findCompetitionPage(start, pageSize, seriesTag, categoryTag, fieldTag, difficultyTag);
        List<Competition> competitionList = competitionMapper.findCompetitionPage(start, pageSize, queryWrapper);
        Page<Competition> competitionPage = new Page<>();
        competitionPage.setRecords(competitionList);
        competitionPage.setTotal(count);
        return competitionPage;
    }


    @Autowired
    public void setCompetitionMapper(CompetitionMapper competitionMapper) {
        this.competitionMapper = competitionMapper;
    }

    @Autowired
    public void setCompetitionTagMapper(CompetitionTagMapper competitionTagMapper) {
        this.competitionTagMapper = competitionTagMapper;
    }
}
