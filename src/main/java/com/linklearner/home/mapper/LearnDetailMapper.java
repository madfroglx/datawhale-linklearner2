package com.linklearner.home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linklearner.home.entity.LearnDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LearnDetailMapper extends BaseMapper<LearnDetail> {

    @Select("""
            <script>
            select id, learn_id, title from learn_detail where learn_id in
            <foreach collection='learnIds' item='learnId' open='(' separator=',' close=')'>
            #{learnId}
            </foreach>
            order by 'index' asc
            </script>
            """)
    List<LearnDetail> findByLearnIds(List<Long> learnIds);
}
