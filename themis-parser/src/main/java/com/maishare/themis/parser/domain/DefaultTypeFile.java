/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.domain;

import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.parser.processor.TypeFileProcessor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * 资源文件
 * @author moushaokun
 * @version @Id: DefaultTypeFile.java, v 0.1 2020年03月11日 10:46 moushaokun Exp $
 */
public class DefaultTypeFile implements TypeFile {
    
    @Setter
    @Getter
    private FileType          type;

    @Setter
    @Getter
    private String            name;

    @Setter
    @Getter
    private File              file;

    @Setter
    private TypeFileProcessor processor;

    @Override
    public <T> T getOriginal() {
        return (T)processor.process(this);
    }
}

