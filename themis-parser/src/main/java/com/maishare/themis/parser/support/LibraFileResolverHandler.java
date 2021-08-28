/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.support;

import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.common.exception.FileParseException;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.extension.ExtensionLoader;
import com.maishare.themis.parser.domain.DefaultTypeFile;
import com.maishare.themis.parser.processor.TypeFileProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * libraFile 解析处理器
 *
 * @author hejianbing
 * @version @Id: LibraFileResolverHandler.java, v 0.1 2020年03月11日 22:31 hejianbing Exp $
 */
public enum LibraFileResolverHandler {
	
	PREPARE() {
		@Override
		protected boolean matchers(File file) {
			return file.isDirectory() && file.getName().toUpperCase()
				.equals(LibraFileResolverHandler.PREPARE.toString().toUpperCase());
		}
		
		@Override
		protected LibraFile handle(LibraFile libraFile, List<TypeFile> typeFileList) {
			libraFile.setLibraPrepareFiles(typeFileList);
			return libraFile;
		}
	},
	MOCK() {
		@Override
		protected boolean matchers(File file) {
			return file.isDirectory() && file.getName().toUpperCase()
				.equals(LibraFileResolverHandler.MOCK.toString().toUpperCase());
		}
		
		@Override
		protected LibraFile handle(LibraFile libraFile, List<TypeFile> typeFileList) {
			libraFile.setLibraMockFiles(typeFileList);
			return libraFile;
		}
		
	},
	CHECK() {
		@Override
		protected boolean matchers(File file) {
			return file.isDirectory() && file.getName().toUpperCase()
				.equals(LibraFileResolverHandler.CHECK.toString().toUpperCase());
		}
		
		@Override
		protected LibraFile handle(LibraFile libraFile, List<TypeFile> typeFileList) {
			libraFile.setLibraCheckFiles(typeFileList);
			return libraFile;
		}
		
	},
	CLEAR() {
		@Override
		protected boolean matchers(File file) {
			return file.isDirectory() && file.getName().toUpperCase()
				.equals(LibraFileResolverHandler.CLEAR.toString().toUpperCase());
		}
		
		@Override
		protected LibraFile handle(LibraFile libraFile, List<TypeFile> typeFileList) {
			libraFile.setLibraClearFiles(typeFileList);
			return libraFile;
		}
		
	},
	LIBRA() {
		@Override
		protected boolean matchers(File file) {
			if (!file.isFile()) {
				return false;
			}
			return getFilePrefix(file).equals(LibraFileResolverHandler.LIBRA.toString().toUpperCase());
		}
		
		@Override
		protected LibraFile handle(LibraFile libraFile, List<TypeFile> typeFileList) {
			libraFile.setLibraLogicFile(typeFileList.get(0));
			return libraFile;
		}
		
	},
	DATA() {
		@Override
		protected boolean matchers(File file) {
			if (!file.isFile()) {
				return false;
			}
			boolean isYaml = getFilePrefix(file).equals(FileType.YAML.toString().toUpperCase());
			return isYaml;
		}
		
		@Override
		protected LibraFile handle(LibraFile libraFile, List<TypeFile> typeFileList) {
			libraFile.setLibraDataFile(typeFileList.get(0));
			return libraFile;
		}
	};

	protected abstract boolean matchers(File file);
	
	protected abstract LibraFile handle(LibraFile libraFile, List<TypeFile> typeFileList);
	
	
	public static LibraFileResolverHandler getLibraFileHandle(File file) {
		for (LibraFileResolverHandler libraFileResolverHandler : values()) {
			if (libraFileResolverHandler.matchers(file)) {
				return libraFileResolverHandler;
			}
		}
		throw new FileParseException("libra资源文件格式不合法");
	}
	
	
	public void resolveLibraFile(LibraFile libraFile, File... libraFileList) {
		List<TypeFile> typeFileList = new ArrayList<>();
		
		for (File file : libraFileList) {
			if (!file.isFile()) {
				continue;
			}
			FileType fileType = FileType.get(getFilePrefix(file));
			if (fileType == null) {
				continue;
			}
			
            TypeFileProcessor adaptiveTpeFileProcessor = ExtensionLoader
                .getExtensionLoader(TypeFileProcessor.class).getAdaptiveExtension();			
			
			DefaultTypeFile typeFile = new DefaultTypeFile();
			typeFile.setFile(file);
			typeFile.setName(file.getName());
			typeFile.setType(fileType);
			typeFile.setProcessor(adaptiveTpeFileProcessor);
			
			typeFileList.add(typeFile);
		}
		if (CollectionUtils.isNotEmpty(typeFileList)) {
			this.handle(libraFile, typeFileList);
		}
	}
	
	public static String getFilePrefix(File file) {
		String fileName = file.getName();
		
		String prefix = StringUtils.substringAfterLast(fileName, ".");
		
		return prefix.toUpperCase();
	}
	
}