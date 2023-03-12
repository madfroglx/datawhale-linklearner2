package com.linklearner.home.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CompetitionTag {
    // 标签id
    private Long id;
    // 标签名称
    private String name;
    // 竞赛标签种类，0：系列，1：类别，2：领域，3：难度
    private Integer type;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;

    public enum COMPETITION_TAG_TYPE {
        SERIES(0, "系列"),
        CATEGORY(1, "类别"),
        FIELD(2, "领域"),
        DIFFICULTY(3, "难度");

        private int value;
        private String name;

        COMPETITION_TAG_TYPE(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getNameByValue(int value) {
            for (COMPETITION_TAG_TYPE type : COMPETITION_TAG_TYPE.values()) {
                if (type.getValue() == value) {
                    return type.getName();
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }
}
