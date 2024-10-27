package com.jingdianjichi.subject.common.enums;

import lombok.Getter;

@Getter
public enum CategoryCodeEnum {

    /**
     * 一级分类
     */
    FIRST_CATEGORY(1, "一级分类"),

    /**
     * 二级分类
     */
    SECOND_CATEGORY(2, "二级分类");

    private Integer code;

    private String desc;

    CategoryCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
