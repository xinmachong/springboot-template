package com.xinmachong.template.consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DataBaseEnums {

    // 状态
    UNABLE(0, "禁用"),
    ENABLE(1, "启用"),
    ;

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
