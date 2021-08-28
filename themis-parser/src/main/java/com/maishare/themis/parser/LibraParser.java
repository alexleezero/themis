/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.domain.Libra;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.extension.SPI;

import java.util.List;

/**
 * libra解析器接口
 * @author lijian
 * @version @Id: LibraParser.java, v 0.1 2020年03月03日 09:33 lijian Exp $
 */
@SPI
public interface LibraParser {
    
    List<Libra> parse(ThemisContext themisContext, List<LibraFile> libraFiles);

}
