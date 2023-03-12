package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class LearnVideo {
    // 学习视频id
    private Long id;
    // 学习id
    private Long learnId;
    // 视频序号
    @TableField("`index`")
    private Integer index;
    // 视频标题
    private String title;
    // 视频url
    private String videoUrl;
    // 视频封面url
    private String imageUrl;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;


}
