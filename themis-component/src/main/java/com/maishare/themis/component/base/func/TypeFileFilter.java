/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.func;

import com.maishare.themis.context.domain.TypeFile;

import java.util.List;

/**
 * 文件过滤器
 * @author moushaokun
 * @version @Id: TypeFileFilter.java, v 0.1 2020年03月21日 18:50 moushaokun Exp $
 */
public interface TypeFileFilter {

    TypeFile doFilter(List<TypeFile> typeFileList, String fileName);
}
