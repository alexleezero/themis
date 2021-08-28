/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker.interceptor;

import com.maishare.themis.common.exception.AssertMatcherException;
import com.maishare.themis.component.base.checker.MatcherContext;
import com.maishare.themis.extension.SPI;
import org.springframework.core.PriorityOrdered;

/**
 * 断言匹配拦截器
 * @author hejianbing
 * @version @Id: AssertMatcherInterceptor.java, v 0.1 2020年03月23日 15:07 hejianbing Exp $
 */
@SPI
public interface AssertMatcherInterceptor  extends PriorityOrdered {

    @Override
    default int getOrder(){
        return 0;
    }

    default boolean preHandle(MatcherContext matcherContext) {
        return true;
    }

    default void postHandle(MatcherContext matcherContext) {}

    default void afterCompletion(MatcherContext matcherContext, Exception ex) {
        throw new AssertMatcherException(ex.getMessage());
    }

}