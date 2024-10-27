package com.jingdianjichi.subject.common.enums;

import lombok.Getter;

@Getter
public enum IsDeletedCodeEnum {

    /**
     * 未删除
     */
    NOT_DELETED(0, "未删除"),

    /**
     * 已删除
     */
    DELETED(1, "已删除");

    private Integer code;

    private String desc;

    IsDeletedCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static IsDeletedCodeEnum getByCode(Integer code) {
        for (IsDeletedCodeEnum isDeletedCodeEnum : IsDeletedCodeEnum.values()) {
            if (isDeletedCodeEnum.code.equals(code)) return isDeletedCodeEnum;
        }
        return null;
    }
}
