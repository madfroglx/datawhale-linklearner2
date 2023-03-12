package com.linklearner.home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linklearner.home.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
}
