package com.linklearner.home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linklearner.home.entity.LearnTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LearnTagMapper extends BaseMapper<LearnTag> {

    @Select("""
            <script>
            select * from learn_tag where id in
            <foreach collection='ids' item='id' open='(' separator=',' close=')'>
            #{id}
            </foreach>
            </script>
            """)
    List<LearnTag> findByIds(List<Long> ids);

}
