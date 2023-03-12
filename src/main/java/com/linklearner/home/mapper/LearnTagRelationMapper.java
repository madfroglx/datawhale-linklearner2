package com.linklearner.home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linklearner.home.entity.LearnTagRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LearnTagRelationMapper extends BaseMapper<LearnTagRelation> {
    @Select("""
            <script>
            select * from learn_tag_relation where learn_id in
            <foreach collection='learnIds' item='learnId' open='(' separator=',' close=')'>
            #{learnId}
            </foreach>
            </script>
            """)
    List<LearnTagRelation> findByLearnIds(List<Long> learnIds);

}
