package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_action")
public class UserAction {
    @TableId(type = IdType.AUTO)
    private Long id;
    // 用户id
    private String sessionId;
    // 用户上报的日志列表
    private String logList;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;


}
