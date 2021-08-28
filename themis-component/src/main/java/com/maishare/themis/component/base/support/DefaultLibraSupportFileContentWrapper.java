/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.support;

import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.convert.TypeConverterExecutor;
import com.maishare.themis.component.base.func.LibraSupportFileContentWrapper;
import com.maishare.themis.component.base.func.TypeFileFilter;
import com.maishare.themis.context.domain.TypeFile;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * libra data File 文件内容包装处理器
 * @author hejianbing
 * @version @Id: DefaultLibraSupportFileContentWrapper.java, v 0.1 2020年03月21日 10:50 hejianbing Exp $
 */
public class DefaultLibraSupportFileContentWrapper implements LibraSupportFileContentWrapper {
	
    private final static  TypeFileFilter typeFileFilter = new DefaultTypeFileFilter();

	@Override
	public Map<String, Object> wrap(LibraSupportFileContentContext libraSupportFileContent) {
		TypeFile dataFile = typeFileFilter.doFilter(libraSupportFileContent.getDataFileList(), libraSupportFileContent.getFileName());
		
		List<Map<String, Object>> data = TypeConverterExecutor.getInstance().convert(dataFile.getOriginal(),List.class);
		
		List<Map<String, Object>> dataList = data.stream().filter(dataItem -> {
			Integer dataIndex = (Integer)dataItem.get(libraSupportFileContent.getIndexKey());
			Object prepareData = dataItem.get(libraSupportFileContent.getDataKey());
			if (null != dataIndex && prepareData != null) {
				if (dataIndex.equals(Integer.valueOf(libraSupportFileContent.getIndex()))) {
					return true;
				}
			}
			return false;
		}).collect(Collectors.toList());
		
        if (CollectionUtils.isNotEmpty(dataList)) {
            if (dataList.get(0).get(libraSupportFileContent.getDataKey()) == null) {
                throw new ComponentException(String.format(
                    "libra 数据文件【%s】未找到【%s】的数据项定义内容",
                    libraSupportFileContent.getFileName(), libraSupportFileContent.getDataKey()));
            }
            return dataList.get(0);
        }
        throw new ComponentException(String.format("libra 数据文件未找到【%s】和data【%s】定义项",
	        libraSupportFileContent.getIndexKey(), libraSupportFileContent.getDataKey()));
	}
	
	@Override
	public boolean support(List<TypeFile> typeFileList) {
        List<TypeFile> typeFiles = typeFileList.stream()
            .filter(typeFile -> typeFile.getType().equals(FileType.YAML))
            .collect(Collectors.toList());
        
		return typeFiles.size() == typeFileList.size();
	}

}