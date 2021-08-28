/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker;

import com.maishare.themis.common.exception.AssertMatcherException;
import com.maishare.themis.common.exception.ThemisException;
import com.maishare.themis.extension.utils.ReflectUtils;
import org.apache.commons.lang3.CharUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 对象类型匹配器
 * @author hejianbing
 * @version @Id: ObjectAssertMatcher.java, v 0.1 2020年03月20日 21:41 hejianbing Exp $
 */
public class ObjectAssertMatcher extends BaseAssertMatcher {

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public EnumConditionalMatcher typeMatch() {
        return EnumConditionalMatcher.OBJECT;
    }

    @Override
    public void assertMatch(MatcherContext matcherContext, AssertMatcherChain matcherChain) {
        Object source = this.convert(matcherContext.getActual(),
            matcherContext.getActual().getClass());
        Object target = this.convert(matcherContext.getExpected(),
            matcherContext.getActual().getClass());

        StringBuilder consoleMessage = new StringBuilder();
        consoleMessage.append(CharUtils.LF + String.format("【断言匹配器】:%s", this.getClass().getName()) + CharUtils.LF );
        consoleMessage.append(CharUtils.LF + String.format("【断言比较对象】:%s,%s", target.getClass().getSimpleName(),
                source.getClass().getSimpleName()) + CharUtils.LF);

        matcherContext.addCompareObjectNames();

        try {
            for (Field targetField : ReflectUtils.getBeanPropertyFields(target.getClass())
                .values()) {
                targetField.setAccessible(true);
                try {
                    Object o1 = targetField.get(target);
                    consoleMessage.append(CharUtils.LF +"【断言匹配属性名】 " + targetField.getName() + ""+ CharUtils.LF);
                    if (null == o1) {
                        continue;
                    }
                    LOG.info(consoleMessage.toString());
                    Field sourceField = ReflectionUtils.findField(source.getClass(),targetField.getName());
                    if (null == sourceField) {
                        throw new AssertMatcherException(String.format("the compare object does not find the same field name %s", targetField.getName()));
                    }
                    sourceField.setAccessible(true);
                    Object o2 = sourceField.get(source);
                    matcherContext.setActual(o2);
                    matcherContext.setExpected(o1);

                    matcherChain.match(matcherContext);

                } catch (Exception e) {
                    throw new ThemisException("assert fail " + e.getMessage() + "");
                }

            }
        } finally {
            matcherContext.getCompareObjectNames().pop();
        }

    }
}