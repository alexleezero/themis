package com.maishare.themis.component.utils;

import com.maishare.themis.common.constants.LibraSupportFileDataConstants;
import com.maishare.themis.common.exception.ParamException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author hejianbing
 * @version @Id: MockDataValid.java, v 0.1 2020年03月24日 11:57 hejianbing Exp $
 */
public final class MockDataValid {
	
	private MockDataValid(){}
	
	public static void checkNotNull(Map<String,Object> data){
		Object bean = data.get(LibraSupportFileDataConstants.BEAN);
		Object method = data.get(LibraSupportFileDataConstants.METHOD);
		Object hostBean = data.get(LibraSupportFileDataConstants.HOST_BEAN);

		objectNotNull(hostBean,"hostBean");
		objectNotNull(bean,"bean");
		objectNotNull(method,"method");
	}

	private static void notNull(String name){
		if (StringUtils.isBlank(name)) {
			throw new ParamException(String.format("libra mock data %s is null",name));
		}
	}
	
	private static void objectNotNull(Object obj,String message){
		if (obj == null ) {
			throw new ParamException(String.format("libra mock data %s is null",message));
		}
	}
	
}