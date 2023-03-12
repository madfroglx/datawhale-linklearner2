package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class LearnDetail {
    //学习章节id
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    //学习id
    private Long learnId;
    //章节序号
    @TableField("`index`")
    private Integer index;
    //章节标题
    private String title;
    //章节内容，md5格式
    private String content;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;

}
