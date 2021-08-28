/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.processor;

import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;

/**
 * TypeFileProcessor 自适应拓展类
 * @author hejianbing
 * @version @Id: AdaptiveTypeProcessor.java, v 0.1 2020年03月17日 13:47 hejianbing Exp $
 */
@Adaptive
public class AdaptiveTypeProcessor implements TypeFileProcessor<Object> {
	
	
    @Override
    public Object doProcess(TypeFile typeFile) {
        ExtensionLoader<TypeFileProcessor> typeFileProcessor = ExtensionLoader
            .getExtensionLoader(TypeFileProcessor.class);

        return typeFileProcessor.getExtension(typeFile.getType().getCode()).doProcess(typeFile);
    }
}