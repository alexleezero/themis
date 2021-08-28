/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maishare.themis.common.exception.FileParseException;
import com.maishare.themis.context.domain.TypeFile;
import org.apache.commons.io.FileUtils;

import java.io.IOException;

/**
 * json文件解析
 * @author hejianbing
 * @version @Id: JsonTypeFileProcessor.java, v 0.1 2020年03月12日 17:13 hejianbing Exp $
 */
public class JsonTypeFileProcessor implements TypeFileProcessor<JSONObject> {
	@Override
	public JSONObject doProcess(TypeFile typeFile) {
		try {
			String content =  FileUtils.readFileToString(typeFile.getFile(), "UTF-8");
			
			return JSON.parseObject(content);
		} catch (IOException e) {
			throw new FileParseException(
				String.format("libra 测试加载json文件出错:%s", e.getMessage()));
		}
	}
}