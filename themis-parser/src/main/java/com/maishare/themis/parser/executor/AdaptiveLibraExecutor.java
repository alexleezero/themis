/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor;

import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;

/**
 * libra antlr 执行器 自适应拓展类
 * @author moushaokun
 * @version @Id: AdaptiveLibraExecutor.java, v 0.1 2020年03月17日 23:56 moushaokun Exp $
 */
@Adaptive
public class AdaptiveLibraExecutor implements LibraExecutor{

    @Override
    public void execute(ThemisTestExecution themisTestExecution) {
        ExtensionLoader
            .getExtension(LibraExecutor.class, "libraAntlrExecutor")
            .execute(themisTestExecution);
    }
}
