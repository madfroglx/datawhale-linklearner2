package com.linklearner.home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linklearner.home.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("select * from article where id = #{id}")
    Article findById(Long id);

}
