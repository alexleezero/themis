/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.common.enums;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 * libra组件类型
 * @author moushaokun
 * @version @Id: LibraCompType.java, v 0.1 2020年03月11日 11:04 moushaokun Exp $
 */
public enum LibraCompType {
    PREPARE("准备组件", "prepare"),
    MOCK("MOCK组件", "mock"),
    EXECUTE("执行组件", "execute"),
    CHECK("检查组件", "check"),
    CLEAR("清理组件", "clear"),
    PRINT("打印组件", "print"),
    FREESTYLE("自定义组件", "freestyle");

    LibraCompType(String typeName, String typeCode) {
        this.typeName = typeName;
        this.typeCode = typeCode;
    }

    @Getter
    private String typeName;

    @Getter
    private String typeCode;

    public static LibraCompType getLibraCompType(String typeCode) {
        if (StringUtils.isBlank(typeCode))
            return null;

        for (LibraCompType value : values()) {
            if (value.typeCode.equals(typeCode)) {
                return value;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.typeCode;
    }
}

