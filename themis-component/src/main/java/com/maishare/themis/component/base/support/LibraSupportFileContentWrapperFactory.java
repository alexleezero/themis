/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.support;

import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.func.LibraSupportFileContentWrapper;
import com.maishare.themis.extension.ExtensionLoader;
import com.maishare.themis.extension.utils.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件内容包装工厂
 * @author hejianbing
 * @version @Id: LibraSupportFileContentWrapperFactory.java, v 0.1 2020年03月23日 12:17 hejianbing Exp $
 */
public class LibraSupportFileContentWrapperFactory {
    
    private static List<LibraSupportFileContentWrapper> libraSupportFileContentWrappers = ExtensionLoader
        .getExtensionLoader(LibraSupportFileContentWrapper.class)
            .getSupportedExtensionInstances()
            .stream().collect(Collectors.toList());
    
    
    public static  LibraSupportFileContentWrapper getInstance(LibraSupportFileContentContext fileContentContext) {
        if (CollectionUtils.isEmpty(fileContentContext.getDataFileList())) {
            throw new ComponentException("LibraSupportFileContentContext dataFileList is null");
        }
        for (LibraSupportFileContentWrapper libraSupportFileContentWrapper : libraSupportFileContentWrappers) {
        
            if (libraSupportFileContentWrapper.support(fileContentContext.getDataFileList())){
                return libraSupportFileContentWrapper;
            }
        
        }
        throw new ComponentException("libraSupportFileContentWrapper is null");
    }
    
}