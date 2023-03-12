package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleTagRelation {
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    // 文章id
    private Long articleId;
    // 标签id
    private Long articleTagId;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;
}
