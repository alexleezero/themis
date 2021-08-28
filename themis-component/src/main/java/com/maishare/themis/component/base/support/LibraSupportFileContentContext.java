/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.support;

import com.maishare.themis.common.constants.LibraSupportFileDataConstants;
import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.context.domain.TypeFile;

import java.util.List;

/**
 * libra 运行支撑文件内容上下文
 * @author hejianbing
 * @version @Id: LibraSupportFileContentContext.java, v 0.1 2020年03月21日 10:56 hejianbing Exp $
 */
public class LibraSupportFileContentContext {

    /** 文件类型 */
    private FileType       fileType;

    /** check prepare mock 数据 */
    private List<TypeFile> dataFileList;

    /** 文件名 */
    private String         fileName;

    /** 索引值 */
    private String         index;

    /** 数据文件index key */
    private String         indexKey = LibraSupportFileDataConstants.INDEX;

    /** 数据文件 data的 key**/
    private String         dataKey  = LibraSupportFileDataConstants.DATA;

    public static LibraSupportFileContentContext newInstance(String index, String fileName) {
        LibraSupportFileContentContext libraFileDataContext = new LibraSupportFileContentContext()
            .setIndex(index).setFileName(fileName);

        return libraFileDataContext;
    }

    public List<TypeFile> getDataFileList() {
        return dataFileList;
    }

    public LibraSupportFileContentContext setDataFileList(List<TypeFile> dataFileList) {
        this.dataFileList = dataFileList;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public LibraSupportFileContentContext setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getIndex() {
        return index;
    }

    public LibraSupportFileContentContext setIndex(String index) {
        this.index = index;
        return this;
    }

    public String getIndexKey() {
        return indexKey;
    }

    public LibraSupportFileContentContext setIndexKey(String indexKey) {
        this.indexKey = indexKey;
        return this;
    }

    public String getDataKey() {
        return dataKey;
    }

    public LibraSupportFileContentContext setDataKey(String dataKey) {
        this.dataKey = dataKey;
        return this;
    }

    public FileType getFileType() {
        return this.fileType;
    }

    public LibraSupportFileContentContext setFileType(FileType fileType) {
        this.fileType = fileType;
        return this;
    }
}