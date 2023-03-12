package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Learn {
    // 学习id
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    // 学习项目排序id
    private Long oid;
    // 学习名称
    private String name;
    // 学习描述
    private String description;
    // 学习封面图
    private String imageUrl;
    // 学习点赞数
    @TableField("`like`")
    private Integer like;
    // 开源协议
    private String license;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;

    public Learn(Long id) {
        this.id = id;
    }
}
