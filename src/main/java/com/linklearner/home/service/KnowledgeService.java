package com.linklearner.home.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linklearner.home.entity.Knowledge;
import com.linklearner.home.mapper.KnowledgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KnowledgeService {
    private KnowledgeMapper knowledgeMapper;

    public List<Map<String, Object>> findAvailableKnowledge() {
        return knowledgeMapper.selectList(
                        new QueryWrapper<Knowledge>().eq("type", Knowledge.KNOWLEDGE_TYPE.DETAIL.getCode()))
                .stream()
                .map(item -> Map.<String, Object>of(
                        "id", item.getId(),
                        "name", item.getName(),
                        "content", item.getContent()))
                .toList();
    }

    public List<Map<String, Object>> findAvailableKnowledgeMind() {
        return knowledgeMapper.selectList(
                        new QueryWrapper<Knowledge>().eq("type", Knowledge.KNOWLEDGE_TYPE.MIND.getCode()))
                .stream()
                .map(item -> Map.<String, Object>of(
                        "id", item.getId(),
                        "name", item.getName(),
                        "content", item.getContent()))
                .toList();
    }

    @Autowired
    public void setKnowledgeMapper(KnowledgeMapper knowledgeMapper) {
        this.knowledgeMapper = knowledgeMapper;
    }
}
