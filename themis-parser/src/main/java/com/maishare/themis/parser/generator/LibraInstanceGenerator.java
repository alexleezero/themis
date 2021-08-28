/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.generator;

import java.util.List;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.context.domain.LibraInstance;
import com.maishare.themis.extension.SPI;

/**
 * libra 实例生成器接口
 * @author moushaokun
 * @version @Id: LibraInstanceGenerator.java, v 0.1 2020年03月12日 10:59 moushaokun Exp $
 */
@SPI
public interface LibraInstanceGenerator {

    List<LibraInstance> generate(ThemisContext themisContext, LibraFile libraFile);

    boolean matches(LibraFile libraFile);

}

