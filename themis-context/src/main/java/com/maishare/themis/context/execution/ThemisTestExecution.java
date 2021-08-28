/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.execution;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.domain.LibraData;
import com.maishare.themis.context.domain.LibraFile;

/**
 * 测试用例运行逻辑类
 * @author lijian
 * @version @Id: ThemisTestExecution.java, v 0.1 2020年01月17日 14:00 lijian Exp $
 */
public interface ThemisTestExecution {

    /**
     * 获取libra文件
     */
    LibraFile getLibraFile();

    /**
     * 设置libra文件
     */
    void setLibraFile(LibraFile libraFile);

    /**
     * 获取运行数据
     */
    LibraData getLibraData();

    /**
     * 设置运行数据
     */
    void setLibraData(LibraData libraData);

    /**
     * 获取themis上下文
     */
    ThemisContext getThemisContext();

    /**
     * 设置themis上下文
     */
    void setThemisContext(ThemisContext themisContext);
    
   

}
