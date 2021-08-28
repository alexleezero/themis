/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker;

import com.maishare.themis.extension.SPI;

/**
 * 断言过滤链接口定义
 * @author hejianbing
 * @version @Id: AssertMatcherChain.java, v 0.1 2020年03月20日 22:46 hejianbing Exp $
 */
@SPI
public interface AssertMatcherChain {
    
    void match(MatcherContext matcherContext);

    boolean addAssertMatcher(AssertMatcher matcher);

    boolean remove(Class<? extends AssertMatcher> classType);
}