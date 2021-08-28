/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.mock;

import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.extension.ExtensionLoader;

import java.util.Optional;
import java.util.Set;

/**
 * mock 处理器工厂
 * @author hejianbing
 * @version @Id: MockFactory.java, v 0.1 2020年03月25日 14:13 hejianbing Exp $
 */
public final class MockFactory {

    private static final Set<MockProcessor> mockProcessorList
            = ExtensionLoader.getExtensionLoader(MockProcessor.class).getSupportedExtensionInstances();



	// todo 支持外部配置
	private final MockType mockType = MockType.MOCKITO;

    public static MockProcessor getMockProcessor() {
        Optional<MockProcessor> processor = mockProcessorList.stream()
            .filter(mockProcessor -> mockProcessor.type() == MockFactoryHolder.MOCK_FACTORY.mockType)
            .findFirst();

        if (processor.isPresent()) {
            return processor.get();
        }
        throw new ComponentException("mockType type not support");
    }

    public static  class MockFactoryHolder{
        private static final MockFactory MOCK_FACTORY = new MockFactory();
    }
}