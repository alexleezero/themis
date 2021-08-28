/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.support;

import com.maishare.themis.common.exception.LibraCompException;
import com.maishare.themis.component.base.func.TypeFileFilter;
import com.maishare.themis.context.domain.TypeFile;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * 文件过滤默处理认实现
 * @author hejianbing
 * @version @Id: DefaultTypeFileFilter.java, v 0.1 2020年03月23日 13:08 hejianbing Exp $
 */
public class DefaultTypeFileFilter implements TypeFileFilter {
	
	@Override
	public TypeFile doFilter(List<TypeFile> typeFileList, String fileName){
		
		if (StringUtils.isEmpty(fileName) || CollectionUtils.isEmpty(typeFileList)) {
			throw new LibraCompException(String.format("数据文件【%s】不存在", fileName));
		}
		
		Optional<TypeFile> dataFile = typeFileList.stream()
			.filter(typeFile -> typeFile.getName().toUpperCase()
				.equals(fileName.toUpperCase()))
			.findFirst();
		
		if (!dataFile.isPresent()) {
			throw new LibraCompException(String.format("数据文件【%s】不存在",fileName));
		}
		
		return dataFile.get();
	}
}