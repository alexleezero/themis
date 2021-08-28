/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.domain;

import java.io.File;

import com.maishare.themis.common.enums.FileType;

/**
 * 限定类型的资源文件接口
 * @author moushaokun
 * @version @Id: TypeFile.java, v 0.1 2020年03月10日 15:47 moushaokun Exp $
 */
public interface TypeFile {

    /**
     * 获取文件类型
     */
    FileType getType();

    /**
     * 获取文件名称
     */
    String   getName();

    /**
     * 获取文件
     */
    File     getFile();

    /**
     * 获取文件内容
     */
    <T> T getOriginal();

}
