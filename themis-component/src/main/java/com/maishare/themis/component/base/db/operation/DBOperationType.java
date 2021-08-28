/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.operation;

import lombok.Getter;

/**
 * 数据库操作类型
 * @author moushaokun
 * @version @Id: OperationType.java, v 0.1 2020年03月19日 10:34 moushaokun Exp $
 */
public enum DBOperationType implements OperationType {
    INSERT("insert"),UPDATE("update"),DELETE("delete"),SELECT("select");

    @Getter
    private String code;

    private DBOperationType(String code){
        this.code = code;
    }

    public static DBOperationType get(String type) {
        for (DBOperationType fileType : values()) {
            if (fileType.toString().toUpperCase().equals(type.toUpperCase())) {
                return fileType;
            }
        }
        return null;
    }
}

