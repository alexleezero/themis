/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker;

import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.checker.interceptor.AssertMatcherInterceptor;
import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;
import org.springframework.core.OrderComparator;

import java.util.*;

/**
 * 断言匹配过滤链实现
 * @author hejianbing
 * @version @Id: AssertMatcherExecutionChain.java, v 0.1 2020年03月20日 21:37 hejianbing Exp $
 */
@Adaptive
public class AssertMatcherExecutionChain implements AssertMatcherChain {

    private static final Map<Class<? extends AssertMatcher>, AssertMatcher> assertMatchers     = new LinkedHashMap<>();

    private static final List<AssertMatcherInterceptor>                     interceptorHandles = new ArrayList<>();

    static {
        initDefaultMatchers();
        initInterceptor();
    }

    @Override
    public void match(MatcherContext matcherContext) {
        if (this.applyPreInterceptor(matcherContext)) {
            AssertMatcher assertMatcher = this.getAssertMatcher(matcherContext);
            try {
                assertMatcher.assertMatch(matcherContext, this);
                this.applyPostInterceptor(matcherContext);
            } catch (Exception e) {
                this.applyAfterCompletion(matcherContext, e);
            }

        }
    }

    @Override
    public boolean addAssertMatcher(AssertMatcher matcher) {
        assertMatchers.put(matcher.getClass(), matcher);
        return true;
    }

    @Override
    public boolean remove(Class<? extends AssertMatcher> classType) {
        AssertMatcher assertMatcher = assertMatchers.remove(classType);
        return null != assertMatcher;
    }

    private AssertMatcher getAssertMatcher(MatcherContext matcherContext) {
        Optional<AssertMatcher> assertMatcherHandler = assertMatchers.values().stream()
            .filter(assertMatcher -> assertMatcher.typeMatch().match(matcherContext.getActual()))
            .findFirst();
        if (assertMatcherHandler.isPresent()) {
            return assertMatcherHandler.get();
        }
        throw new ComponentException(
            String.format("no assertion data type matcher found %s", matcherContext.getActual().getClass().getSimpleName()));
    }

    private void applyAfterCompletion(MatcherContext matcherContext, Exception e) {
        for (AssertMatcherInterceptor interceptorHandle : interceptorHandles) {
            interceptorHandle.afterCompletion(matcherContext, e);
        }
    }

    private void applyPostInterceptor(MatcherContext matcherContext) {
        for (AssertMatcherInterceptor interceptorHandle : interceptorHandles) {
            interceptorHandle.postHandle(matcherContext);
        }
    }

    private boolean applyPreInterceptor(MatcherContext matcherContext) {
        for (AssertMatcherInterceptor interceptorHandle : interceptorHandles) {
            if (!interceptorHandle.preHandle(matcherContext)) {
                return false;
            }
        }
        return true;
    }

    private static void initDefaultMatchers() {
        Set<AssertMatcher> defaultAssertMatchers = ExtensionLoader
            .getExtensionLoader(AssertMatcher.class).getSupportedExtensionInstances();

        List<AssertMatcher> assertMatcherList = new ArrayList<>(defaultAssertMatchers);

        assertMatcherList.sort(OrderComparator.INSTANCE);

        for (AssertMatcher assertMatcher : assertMatcherList) {
            assertMatchers.put(assertMatcher.getClass(), assertMatcher);
        }
        System.out.println();
    }

    private static void initInterceptor() {
        Set<AssertMatcherInterceptor> assertMatcherInterceptors = ExtensionLoader
            .getExtensionLoader(AssertMatcherInterceptor.class).getSupportedExtensionInstances();
        interceptorHandles.addAll(assertMatcherInterceptors);
        interceptorHandles.sort(OrderComparator.INSTANCE);
    }

}