package com.maishare.themis.component.base.checker;

import com.maishare.themis.component.base.convert.TypeConverterExecutor;
import com.maishare.themis.component.base.domain.CompareOperator;
import org.bravo.gaia.log.logger.Logger;
import org.bravo.gaia.log.logger.LoggerFactory;
import org.junit.jupiter.api.Assertions;

public abstract class BaseAssertMatcher implements AssertMatcher {

    Logger LOG = LoggerFactory.getLogger(this.getClass());


    <T> T convert(Object source, Class<T> selfType) {
        return  TypeConverterExecutor.getInstance().convert(source, selfType);
    }

    void equal(MatcherContext matcherContext, Comparable s, Comparable t) {
        if (matcherContext.getCompareOperator() == CompareOperator.EQUAL) {
            Assertions.assertTrue(s.compareTo(t) == 0,
                    ()-> String.format("期望值[%s] %s 实际值[%s]",s,matcherContext.getCompareOperator().getCode(),t));
        }
    }

    void greaterThan(MatcherContext matcherContext, Comparable s, Comparable t) {
        if (matcherContext.getCompareOperator() == CompareOperator.GREATER_THAN) {
            Assertions.assertTrue(s.compareTo(t) > 0,()-> String.format("期望值[%s] %s 实际值[%s]",s,matcherContext.getCompareOperator().getCode(),t));
        }
    }
    void lessThan(MatcherContext matcherContext, Comparable s, Comparable t) {
        if (matcherContext.getCompareOperator() == CompareOperator.LESS_THAN) {
            Assertions.assertTrue(s.compareTo(t) < 0,()-> String.format("期望值[%s] %s 实际值[%s]",s,matcherContext.getCompareOperator().getCode(),t));
        }
    }

}
