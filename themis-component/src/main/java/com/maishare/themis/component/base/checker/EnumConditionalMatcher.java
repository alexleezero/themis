/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker;


import java.util.List;
import java.util.Map;

/**
 * 断言枚举条件匹配器
 * @author hejianbing
 * @version @Id: EnumConditionalMatcher.java, v 0.1 2020年03月20日 21:53 hejianbing Exp $
 */
public enum EnumConditionalMatcher implements ConditionalMatcher {
	
	ARRAY_LIST(){
		@Override
		boolean doMatch(Object expected) {
			return  expected.getClass().isArray() ||
					List.class.isAssignableFrom(expected.getClass());
		}
	},
	MAP(){
		@Override
		boolean doMatch(Object expected) {
			return Map.class.isAssignableFrom(expected.getClass());
		}
	},
	OBJECT(){
		@Override
		boolean doMatch(Object expected) {
//			Class<?>[] javaLanguageInterfaceArray = {Serializable.class, Externalizable.class,
//					Closeable.class, AutoCloseable.class, Cloneable.class, Comparable.class};
//
//			return Arrays.stream(javaLanguageInterfaceArray).anyMatch(javaType->javaType.isAssignableFrom(expected.getClass()));

			return true;

		}
	}
	,COMPARABLE(){
		@Override
		boolean doMatch(Object source) {
			return Comparable.class.isAssignableFrom(source.getClass());
		}
	};

	@Override
	public  boolean match(Object source){
		return this.doMatch(source);
	}
	abstract boolean doMatch(Object source);
}