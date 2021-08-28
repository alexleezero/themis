/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker;

import com.maishare.themis.component.utils.MatcherLogUtils;

/**
 * 实现comparable匹配器
 * @author hejianbing
 * @version @Id: ComparableTypeAssertMatcher.java, v 0.1 2020年03月20日 21:36 hejianbing Exp $
 */
public class ComparableTypeAssertMatcher extends BaseAssertMatcher {
    @Override
    public EnumConditionalMatcher typeMatch() {
        return EnumConditionalMatcher.COMPARABLE;
    }

    @Override
    public void assertMatch(MatcherContext matcherContext, AssertMatcherChain matcherChain) {
        Comparable<?> o1 = (Comparable<?>) this.convert(matcherContext.getExpected(), matcherContext.getActual().getClass());
        Comparable<?> o2 = (Comparable<?>) this.convert(matcherContext.getActual(), matcherContext.getActual().getClass());
        MatcherLogUtils.consoleLog(matcherContext,this);
        equal(matcherContext,o1,o2);
        greaterThan(matcherContext,o1,o2);
        lessThan(matcherContext,o1,o2);

    }
}