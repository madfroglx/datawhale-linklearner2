package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class LearnTagRelation {
    //标签id
    @TableId(type = IdType.AUTO)
    private Long id;
    //学习id
    private Long learnId;
    //标签id
    private Long learnTagId;

    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;


}
