/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.locator;

import com.maishare.themis.context.domain.LibraFile;

import java.util.List;

/**
 * libra资源定位器
 * @author moushaokun
 * @version @Id: LibraLocator.java, v 0.1 2020年03月10日 15:40 moushaokun Exp $
 */
public interface LibraLocator {

    List<LibraFile> lookup();
}

