/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.domain;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Libra数据文件领域模型
 * @author lijian
 * @version @Id: LibraData.java, v 0.1 2020年03月03日 09:35 lijian Exp $
 */
@Getter
@Setter
public class LibraData {

    /** 序号，数据用例唯一标识 */
    private String              index;

    /** 描述 */
    private String              desc;

    /** 作者 */
    private String              author;

    /**
     * libra实例运行时数据，值可能是字符串（变量也是字符串）、Map
     * <li>数据在解析libra数据文件时初始化，初始化的值对应一个data case中的data</li>
     * <li>数据在解析执行libra逻辑文件的过程中可能产生变化，变化发生于组件内部对数据的修改</li>
     */
    private Map<String, Object> data;

}
