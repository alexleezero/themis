/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.mock;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.extension.SPI;

import java.util.List;

/**
 * mock 处理器
 * @author hejianbing
 * @version @Id: MockProcessor.java, v 0.1 2020年03月25日 13:45 hejianbing Exp $
 */
@SPI
public interface MockProcessor {

    default MockType type() {
        return MockType.MOCKITO;
    }

    /**
     * mock 生成mock对象
     */
    <T> T createMock(MockContext mockitoContext);

    /**
     * mock 重置
     */
    void resetMock(List<MockMethod> mockMethods, ThemisContext themisContext);

    /**
     * 从ThemisTestExecution 获取匹配的方法
     */
    MockMethodInvokeParam getMatchMethod(MockMethodParamRequest request);


}