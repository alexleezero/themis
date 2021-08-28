/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker;

import org.junit.jupiter.api.Assertions;

import java.util.List;

/**
 * 数组和List集合断言匹配器
 * @author hejianbing
 * @version @Id: ListAssertMatcher.java, v 0.1 2020年03月20日 21:33 hejianbing Exp $
 */
public class ArrayListAssertMatcher extends BaseAssertMatcher {

	@Override
	public EnumConditionalMatcher typeMatch() {
		return EnumConditionalMatcher.ARRAY_LIST;
	}
	
	@Override
	public void assertMatch(MatcherContext matcherContext, AssertMatcherChain matcherChain) {
		List<?> expectedList = this.convert(matcherContext.getExpected(), List.class);
		
		List<?> actualList = this.convert(matcherContext.getActual(),List.class);
		
		for (Object expectedItem : expectedList) {
			boolean isEqual = false;
			for (Object actualItem : actualList) {
				matcherContext.setExpected(expectedItem);
				matcherContext.setActual(actualItem);
				try{
					matcherChain.match(matcherContext);
					isEqual = true;
					break;
				}catch (AssertionError ignored){
				}
			}
			Assertions.assertTrue(isEqual);
		}
	}
}