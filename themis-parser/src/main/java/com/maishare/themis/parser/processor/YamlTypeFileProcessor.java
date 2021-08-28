/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.processor;

import com.maishare.themis.common.exception.FileParseException;
import com.maishare.themis.context.domain.TypeFile;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * yaml 文件解析
 * @author hejianbing
 * @version @Id: YamlTypeFileProcessor.java, v 0.1 2020年03月12日 10:23 hejianbing Exp $
 */
public class YamlTypeFileProcessor implements TypeFileProcessor<List<Map<String,Object>>> {
	
	
	@Override
	public List<Map<String, Object>> doProcess(TypeFile typeFile) {
		Yaml yaml = new Yaml();
		try {
			List<Map<String, Object>> result = new LinkedList<>();
			
			for (Object object : yaml.loadAll(new FileInputStream(typeFile.getFile()))) {
				
				result.add( asMap(object));
			}
			return result;
			
		} catch (FileNotFoundException e) {
			throw new FileParseException(
				String.format("libra 测试加载yaml出错:%s", e.getMessage()));
		}
	}
	
	private static Map<String, Object> asMap(Object object) {
		Map<String, Object> result = new LinkedHashMap<>();
		if (!(object instanceof Map)) {
			result.put("document", object);
			return result;
		}
		
		Map<Object, Object> map = (Map<Object, Object>) object;
		map.forEach((key, value) -> {
			if (value instanceof Map) {
				value = asMap(value);
			}
			if (key instanceof CharSequence) {
				result.put(key.toString(), value);
			} else {
				result.put("[" + key.toString() + "]", value);
			}
		});
		return result;
	}
}