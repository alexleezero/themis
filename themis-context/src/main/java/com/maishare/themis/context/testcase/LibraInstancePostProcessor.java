/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.testcase;

import com.maishare.themis.context.domain.LibraInstance;
import com.maishare.themis.extension.SPI;

/**
 * Libra instance 运行实例后置处理
 * @author hejianbing
 * @version @Id: LibraInstancePostProcessor.java, v 0.1 2020年03月25日 17:34 hejianbing Exp $
 */
@SPI
public interface LibraInstancePostProcessor {
	
	void postProcess(LibraInstance libraInstance);
}