package com.linklearner.home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linklearner.home.entity.Learn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface LearnMapper extends BaseMapper<Learn> {
    @Select("""
            <script>
                select * from learn
                <if test="param != null">
                    <where>
                        <if test="param.id != null">
                            and id = #{param.id}
                        </if>
                        <if test="param.name != null">
                            and name like concat('%', #{param.name}, '%')
                        </if>
                    </where>
               </if>
                <if test="orderBys != null">
                    order by
                    <foreach collection="orderBys" item="item" index="index" separator=",">
                        ${index} ${item}
                    </foreach>
                </if>
                <if test="offset != null and limit != null">
                    limit #{offset}, #{limit}
                </if>
             </script>
             """)
    List<Learn> findLearn(Learn param, Map<String, String> orderBys, Integer offset, Integer limit);
}
