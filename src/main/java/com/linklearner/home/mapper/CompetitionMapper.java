package com.linklearner.home.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linklearner.home.entity.Competition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompetitionMapper extends BaseMapper<Competition> {
//
//    @Select("""
//            <script>
//                SELECT * FROM competition
//                <where>
//                    <if test="seriesTag != null">
//                        AND series_tag = #{seriesTag}
//                    </if>
//                    <if test="categoryTag != null">
//                        AND category_tag = #{categoryTag}
//                    </if>
//                    <if test="fieldTag != null">
//                        AND field_tag = #{fieldTag}
//                    </if>
//                    <if test="difficultyTag != null">
//                        AND difficulty_tag = #{difficultyTag}
//                    </if>
//                </where>
//                ORDER BY oid ASC, id DESC
//                LIMIT #{start}, #{pageSize}
//            </script>
//            """)
//    List<Competition> findCompetitionPage(long start, long pageSize, Integer seriesTag, Integer categoryTag, Integer fieldTag, Integer difficultyTag);

    @Select("""
            <script>
                SELECT * FROM competition
                <if test="ew != null">
                    ${ew.customSqlSegment}
                </if>
                ORDER BY oid ASC, id DESC
                LIMIT #{start}, #{pageSize}
            </script>
            """)
    List<Competition> findCompetitionPage(int start, int pageSize, QueryWrapper<Competition> ew);
}
