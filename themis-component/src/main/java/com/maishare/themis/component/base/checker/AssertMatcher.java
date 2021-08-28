/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker;

import com.maishare.themis.extension.SPI;
import org.springframework.core.PriorityOrdered;

/**数据断言接口定义
 * @author hejianbing
 * @version @Id: AssertMatcher.java, v 0.1 2020年03月20日 21:27 hejianbing Exp $
 */
@SPI
public interface AssertMatcher extends PriorityOrdered {

    @Override
    default int getOrder() {
        return 0;
    }

    /**
     * 匹配条件
     */
    EnumConditionalMatcher typeMatch();

    /**
     * 断言处理
     */
    void assertMatch(MatcherContext matcherContext, AssertMatcherChain matcherChain);
}