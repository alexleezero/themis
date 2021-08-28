/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.mock;

import com.maishare.themis.common.constants.LibraSupportFileDataConstants;
import com.maishare.themis.context.domain.LibraData;
import com.maishare.themis.context.domain.LibraInstance;
import com.maishare.themis.context.testcase.LibraInstancePostProcessor;

import java.util.List;

/**
 * Libra 运行实例后置处理器
 * @author hejianbing
 * @version @Id: MockResetLibraInstancePostProcessor.java, v 0.1 2020年03月25日 17:37 hejianbing Exp $
 */
public class MockResetLibraInstancePostProcessor implements LibraInstancePostProcessor {
	
	@Override
	public void postProcess(LibraInstance libraInstance){
		LibraData libraData = libraInstance.getThemisTestExecution().getLibraData();
		
		Object mockCacheData = libraData.getData().get(LibraSupportFileDataConstants.MOCK_CACHE_DATA);
		
        if (mockCacheData == null) {
            return;
        }
		List<MockMethod> mockMethods = List.class.cast(mockCacheData);

		MockFactory.getMockProcessor()
			.resetMock(mockMethods,libraInstance.getThemisTestExecution().getThemisContext());
	};
}