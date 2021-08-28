/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.context;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 执行上下文
 * @author moushaokun
 * @version @Id: ExecuteContext.java, v 0.1 2020年03月16日 15:24 moushaokun Exp $
 */
@Getter
@Setter
public class ExecuteContext {

    /** 待执行的内容 */
    private List<ExecuteItem> contexts;

    /** 当前执行逻辑块所在文件的引用深度，最外层等于1 */
    private int               depth;

    /** 执行过程是否发生了异常 */
    private boolean           errorOccurs;

    /** 如果发生了异常，记录第一个异常 */
    private Throwable         throwable;

    public ExecuteContext() {
        this.contexts = new ArrayList<>();
        this.depth = 0;
        this.errorOccurs = false;
        this.throwable = null;
    }
}
