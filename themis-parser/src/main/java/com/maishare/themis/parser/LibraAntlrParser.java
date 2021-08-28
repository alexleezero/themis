/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser;

import java.util.ArrayList;
import java.util.List;

import com.maishare.themis.extension.ExtensionLoader;
import org.apache.commons.collections.CollectionUtils;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.domain.Libra;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.parser.generator.LibraInstanceGenerator;

/**
 * libra Antlr 解析器
 * @author moushaokun
 * @version @Id: LibraAntlrParser.java, v 0.1 2020年03月10日 15:56 moushaokun Exp $
 */
public class LibraAntlrParser implements LibraParser{

    @Override
    public List<Libra> parse(ThemisContext themisContext, List<LibraFile> libraFiles) {
        List<Libra> libras = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(libraFiles)) {
            libraFiles.forEach(libraFile -> {
                Libra libra = new Libra();

                libra.setLibraFile(libraFile);

                LibraInstanceGenerator instanceGenerator =
                        ExtensionLoader.getExtensionLoader(LibraInstanceGenerator.class).getAdaptiveExtension();

                libra.setLibraInstanceList(instanceGenerator.generate(themisContext, libraFile));

                libras.add(libra);
            });
        }

        return libras;
    }
}

