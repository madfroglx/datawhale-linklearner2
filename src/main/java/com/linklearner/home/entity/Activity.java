package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Activity {
    // 活动id
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    // 活动名称
    private String name;
    // 活动描述
    private String description;
    // 报名链接
    private String registrationLink;
    // 报名截止时间
    private Date registrationEndTime;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;
}
