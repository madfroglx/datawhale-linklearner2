package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("learn_tag")
public class LearnTag {
    // 标签id
    private Long id;
    // 标签名称
    private String name;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;

}
