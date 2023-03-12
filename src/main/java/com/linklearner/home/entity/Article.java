package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Article {
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    private Integer area;

    private String name;
    private String author;

    private String imageUrl;
    private String url;

    private Date releaseTime;

    private Date createTime;

    private Date modifyTime;
}
