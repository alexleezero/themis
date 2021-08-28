/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.component;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.SPI;

/**
 * Libra组件接口
 * 所有libra的组件多必须实现该接口
 * @author lijian
 * @version @Id: LibraComponent.java, v 0.1 2020年03月03日 09:57 lijian Exp $
 */
@SPI
public interface LibraComponent<LibraComponentParam>{

    /**
     * 执行
     */
    void act(ThemisTestExecution themisTestExecution, LibraComponentParam param);

    /**
     * 名称
     */
    String name();

    /**
     * 类型
     */
    LibraCompType type();
    
}
