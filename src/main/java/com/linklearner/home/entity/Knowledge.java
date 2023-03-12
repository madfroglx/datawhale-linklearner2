package com.linklearner.home.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Knowledge {
    // 培养方案id
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    // 培养方案名称
    private String name;
    // 培养方案内容，json格式
    private String content;
    //培养方案的类型，10为总览，20为具体细节
    private Integer type;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;


    public enum KNOWLEDGE_TYPE {
        MIND(10, "总览"),
        DETAIL(20, "细节");

        private Integer code;
        private String desc;

        KNOWLEDGE_TYPE(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
