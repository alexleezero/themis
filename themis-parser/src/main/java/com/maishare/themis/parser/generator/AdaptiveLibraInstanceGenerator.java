/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.generator;

import java.util.List;
import java.util.Optional;

import com.maishare.themis.common.exception.ThemisException;
import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.context.domain.LibraInstance;
import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;

/**
 * libra 实例生成器 自适应拓展类
 * @author moushaokun
 * @version @Id: AdaptiveLibraInstanceGenerator.java, v 0.1 2020年03月17日 15:52 moushaokun Exp $
 */
@Adaptive
public class AdaptiveLibraInstanceGenerator implements LibraInstanceGenerator{

    @Override
    public List<LibraInstance> generate(ThemisContext themisContext, LibraFile libraFile) {
        Optional<LibraInstanceGenerator> generatorOptional = ExtensionLoader.getExtensionLoader(LibraInstanceGenerator.class)
                .getSupportedExtensionInstances().stream().filter(generator -> generator.matches(libraFile)).findFirst();

        if(!generatorOptional.isPresent()){
            throw new ThemisException("没有匹配的libra实例生成器");
        }

        return generatorOptional.get().generate(themisContext, libraFile);
    }

    @Override
    public boolean matches(LibraFile libraFile) {
        return false;
    }
}

