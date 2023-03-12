package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Competition {
    // 竞赛id
    @TableId(type = IdType.AUTO)
    private Long id;
    // 竞赛排序id
    private Integer oid;
    // 竞赛名称
    private String name;
    // 竞赛描述
    private String description;
    // 竞赛封面图
    private String imageUrl;
    // 竞赛报名链接
    private String joinUrl;
    // 竞赛相关资源链接
    private String resourceUrl;
    // 竞赛截止时间
    private String registrationEndTime;
    // 竞赛奖金
    private String bonus;
    // 系列标签
    private Integer seriesTag;
    // 类别标签
    private Integer categoryTag;
    // 领域标签
    private Integer fieldTag;
    // 难度标签
    private Integer difficultyTag;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;

}
