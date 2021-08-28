/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.factory;

import java.util.Map;
import java.util.Optional;

import com.maishare.themis.common.exception.ThemisException;
import com.maishare.themis.context.component.LibraComponentParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;
import com.maishare.themis.parser.libra.LibraParser;

/**
 * libra组件参数工厂自适应扩展类
 * @author moushaokun
 * @version @Id: AdaptiveLibraComponentParamFactory.java, v 0.1 2020年03月31日 00:28 moushaokun Exp $
 */
@Adaptive
public class AdaptiveLibraComponentParamFactory implements LibraComponentParamFactory {

    @Override
    public boolean support(String compTypeCode, String compName) {
        return false;
    }

    @Override
    public LibraComponentParam createParam(LibraParser.CompDeclContext context,
                                           ThemisTestExecution themisTestExecution,
                                           Map<String, Object> params) {
        Optional<LibraComponentParamFactory> optionalFactory = ExtensionLoader.getExtensionLoader(LibraComponentParamFactory.class)
                .getSupportedExtensionInstances().stream()
                .filter(factory -> factory.support(context.compType().getText(), context.compName().getText()))
                .findFirst();

        if(!optionalFactory.isPresent()){
            throw new ThemisException("没有匹配的libra组件参数工厂");
        }

        return optionalFactory.get().createParam(context, themisTestExecution, params);
    }
}
