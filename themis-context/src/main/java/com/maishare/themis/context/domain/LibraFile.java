/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * libra资源文件
 * @author moushaokun
 * @version @Id: LibraFile.java, v 0.1 2020年03月10日 15:42 moushaokun Exp $
 */
@Getter
@Setter
public class LibraFile {

    /** 类的全限定名 */
    private String         fullName;

    /** 类名 */
    private String         className;

    /** 方法名 */
    private String         methodName;

    /** 唯一标识，类名+方法名 */
    private String         index;

    /** 逻辑文件 */
    private TypeFile       libraLogicFile;

    /** 数据文件 */
    private TypeFile       libraDataFile;

    /** 准备阶段的相关文件 */
    private List<TypeFile> libraPrepareFiles;

    /** mock阶段的相关文件 */
    private List<TypeFile> libraMockFiles;

    /** 检查阶段的相关文件 */
    private List<TypeFile> libraCheckFiles;

    /** 清理阶段的相关文件 */
    private List<TypeFile> libraClearFiles;

}
