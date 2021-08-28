/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.common.enums;

import lombok.Getter;

/**
 * 文件类型
 *
 * @author moushaokun
 * @version @Id: FileType.java, v 0.1 2020年03月10日 15:36 moushaokun Exp $
 */
@Getter
public enum FileType {
	LIBRA("libra"), YAML("yaml"),JSON("json");
	
	private String code;
	
	private FileType(String code){
		this.code = code;
	}
	
	public static FileType get(String type) {
		for (FileType fileType : values()) {
			if (fileType.toString().toUpperCase().equals(type.toUpperCase())) {
				return fileType;
			}
		}
		return null;
	}
	}

