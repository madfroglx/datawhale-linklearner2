package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleTag {
    //文章标签id
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    // 文章标签名称
    private String name;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;
}

