/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor;

import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.SPI;

/**
 * libra 逻辑执行器
 * @author moushaokun
 * @version @Id: LibraExecutor.java, v 0.1 2020年03月17日 16:47 moushaokun Exp $
 */
@SPI
public interface LibraExecutor {

   void execute(ThemisTestExecution themisTestExecution);

}

