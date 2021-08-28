/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker;

import org.apache.commons.lang3.CharUtils;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

/**
 * Map 数据类型断言
 * @author hejianbing
 * @version @Id: MapAssertMap.java, v 0.1 2020年03月20日 21:29 hejianbing Exp $
 */
public class MapAssertMatcher extends BaseAssertMatcher {
	

	@Override
    public EnumConditionalMatcher typeMatch() {
        return EnumConditionalMatcher.MAP;
    }

    @Override
    public void assertMatch(MatcherContext matcherContext, AssertMatcherChain matcherChain) {
        Map<Object, Object> expectedData =this.convert(matcherContext.getExpected(), Map.class);

        Map<Object, Object> actualData = this.convert(matcherContext.getActual(), Map.class);

        for (Map.Entry<Object, Object> expectedEntry : expectedData.entrySet()) {
            Object actualValue = actualData.get(expectedEntry.getKey());

            LOG.info(String.format(CharUtils.LF+"【断言属性名称】:%s", expectedEntry.getKey())+ CharUtils.LF);

            if(actualValue == null){
                Assertions.assertNull(expectedEntry.getValue(),String.format("the key [%s] not found value",expectedEntry.getKey()));
                continue;
            }
            matcherContext.setActual(actualValue);
            matcherContext.setExpected(expectedEntry.getValue());
            matcherChain.match(matcherContext);
        }
    }
}