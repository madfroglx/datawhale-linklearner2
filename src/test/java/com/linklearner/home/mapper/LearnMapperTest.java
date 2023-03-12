package com.linklearner.home.mapper;

import com.linklearner.home.entity.Learn;
import com.linklearner.home.entity.LearnTagRelation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LearnMapperTest {
    @Autowired
    LearnMapper learnMapper;
    @Autowired
    LearnTagRelationMapper learnTagRelationMapper;

    @Test
    void findFiveLikeDesc() {

    }
}