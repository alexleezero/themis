package com.maishare.themis.component.base.checker.interceptor;

import com.maishare.themis.common.exception.AssertMatcherException;
import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.checker.MatcherContext;
import com.maishare.themis.component.base.domain.CompareOperator;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ValidAssertMatcherInterceptor implements AssertMatcherInterceptor {

    private static final List<CompareOperator> compareOperatorList = new ArrayList<>();

    static {
        compareOperatorList.add(CompareOperator.EQUAL);
        compareOperatorList.add(CompareOperator.LESS_THAN);
        compareOperatorList.add(CompareOperator.GREATER_THAN);
    }

    @Override
    public boolean preHandle(MatcherContext matcherContext) {
        this.throwIfNotNeedType(matcherContext);
        return isNotNull(matcherContext);
    }

    private boolean isNotNull(MatcherContext matcherContext) {
        if (null == matcherContext) {
            throw new ComponentException("assert match matcherContext is null");
        }
        if (null == matcherContext.getActual() && null == matcherContext.getExpected()) {
            return false;
        }
        if (null != matcherContext.getExpected() && null == matcherContext.getActual()) {
            Assertions.fail(String.format("expected value  not equal  actual value [%s,%s]",
                matcherContext.getExpected(), null));
        }
        return null != matcherContext.getExpected();
    }

    private void throwIfNotNeedType(MatcherContext matcherContext) {
        CompareOperator compareOperator = matcherContext.getCompareOperator();
        if (null == compareOperator) {
            matcherContext.setCompareOperator(CompareOperator.EQUAL);
            return;
        }
        if (compareOperatorList.contains(compareOperator)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (CompareOperator operator : compareOperatorList) {
            sb.append(operator.getCode()).append(" ");
        }
        throw new AssertMatcherException(
            String.format("supported comparison operators [%s]", sb.toString()));
    }
}
