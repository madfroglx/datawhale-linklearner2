package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Banner {
    // 轮播图id
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    // 轮播图片链接
    private String imageUrl;
    // 轮播图描述
    private String description;
    // 点击跳转链接
    private String linkUrl;
    // 轮播图状态，10可用，20不可用
    private int status;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date modifyTime;


    enum BannerStatus {
        INUSE(10), UNUSE(20);
        private final int status;

        BannerStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }

}
