/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.factory;

import java.util.Map;

import com.maishare.themis.context.component.LibraComponentParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.SPI;
import com.maishare.themis.parser.libra.LibraParser;

/**
 * libra 组件参数工厂
 * @author moushaokun
 * @version @Id: LibraComponentParamFactory.java, v 0.1 2020年03月31日 00:23 moushaokun Exp $
 */
@SPI
public interface LibraComponentParamFactory {

    /**
     * 支持的组件
     * eg：support(LibraCompType.PREPARE.getTypeCode(), LibraCompNameConstants.DB)
     */
    boolean support(String compTypeCode, String compName);

    /**
     * 创建参数
     * @param context   组件定义上下文
     * @param themisTestExecution   themis测试执行上下文
     * @param params    用例执行逻辑参数
     */
    LibraComponentParam createParam(LibraParser.CompDeclContext context,
                                    ThemisTestExecution themisTestExecution,
                                    Map<String, Object> params);
}
