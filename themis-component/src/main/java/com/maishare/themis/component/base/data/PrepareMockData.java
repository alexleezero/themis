/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.data;

import com.maishare.themis.component.enums.MockParamType;
import lombok.Getter;
import lombok.Setter;

/**
 * MockDataFile 数据信息
 * @author hejianbing
 * @version @Id: PrepareMockData.java, v 0.1 2020年03月21日 16:03 hejianbing Exp $
 */
@Setter
@Getter
public class PrepareMockData {

    /** bean名称,全限定名 */
    private String        bean;

    /** 宿主bean对象 */
    private String        hostBean;

    /** 方法名 */
    private String        method;

    /** mock 自定义参数对象与param互斥*/
    private Object        paramType;

    /** mock 自定义参数对象与paramType互斥*/
    private Object        param;

    /** mock 方法返回值 */
    private Object        ret;
    
    /**
     * none 忽略参数匹配
     * param mock数根据参数值进行匹配
     * paramType mock数据根据类型匹配
     */
    private MockParamType mockParamType = MockParamType.NONE;

}