/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.processor;

import com.maishare.themis.common.exception.FileParseException;
import com.maishare.themis.context.domain.TypeFile;
import org.apache.commons.io.FileUtils;

import java.io.IOException;

/**
 * libra file 文件解析
 * @author hejianbing
 * @version @Id: LibraTypeFileProcessor.java, v 0.1 2020年03月12日 11:27 hejianbing Exp $
 */
public class LibraTypeFileProcessor implements TypeFileProcessor<String> {
	
	@Override
	public String doProcess(TypeFile typeFile)  {
		try {
			return FileUtils.readFileToString(typeFile.getFile(), "UTF-8");
		} catch (IOException e) {
			throw new FileParseException(
				String.format("libra 测试加载logic文件出错:%s", e.getMessage()));
		}
	}
}