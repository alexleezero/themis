/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.func;

import com.maishare.themis.component.base.support.LibraSupportFileContentContext;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.extension.SPI;

import java.util.List;

/**
 * libra 文件内容包装器
 * @author hejianbing
 * @version @Id: LibraSupportFileContentWrapper.java, v 0.1 2020年03月21日 10:49 hejianbing Exp $
 */
@SPI
public interface LibraSupportFileContentWrapper{
	
	<R> R wrap(LibraSupportFileContentContext libraSupportFileContent);
	
	boolean support(List<TypeFile> typeFileList);
}