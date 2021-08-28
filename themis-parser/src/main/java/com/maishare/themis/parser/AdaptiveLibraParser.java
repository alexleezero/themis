/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.domain.Libra;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;

import java.util.List;

/**
 * libra antlr 解析器 自适应拓展类
 * @author moushaokun
 * @version @Id: AdaptiveLibraParser.java, v 0.1 2020年03月17日 19:11 moushaokun Exp $
 */
@Adaptive
public class AdaptiveLibraParser implements LibraParser{

    @Override
    public List<Libra> parse(ThemisContext themisContext, List<LibraFile> libraFiles) {
    
        LibraParser libraParser = ExtensionLoader.getExtension(LibraParser.class, "libraAntlrParser");
    
        return libraParser.parse(themisContext, libraFiles);
    }
}

