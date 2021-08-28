/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.checker;

import com.maishare.themis.common.exception.AssertMatcherException;
import com.maishare.themis.component.base.domain.CompareOperator;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

/**
 * 断言匹配参数对象
 * @author hejianbing
 * @version @Id: MatcherContext.java, v 0.1 2020年03月23日 13:49 hejianbing Exp $
 */
@Getter
@Setter
public class MatcherContext {

    /** 期望值 */
    private Object          expected;

    /** 实际值 */
    private Object          actual;

    /** 错误提示 */
    private String          message;

    private CompareOperator compareOperator    = CompareOperator.EQUAL;

    private Stack<String>   compareObjectNames = new Stack<>();

    public void addCompareObjectNames() {
        if (!compareObjectNames.contains(expected.getClass().getName())) {
            compareObjectNames.push(expected.getClass().getName());
            return;
        }
        String message = String.format("assert compare object %s circular dependency",
            expected.getClass().getName());
        throw new AssertMatcherException(message);
    }
}