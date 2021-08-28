/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.execute;

import com.maishare.themis.common.constants.LibraCompNameConstants;
import com.maishare.themis.component.base.mock.MockFactory;
import com.maishare.themis.component.base.mock.MockMethod;
import com.maishare.themis.component.base.mock.MockMethodParamRequest;
import com.maishare.themis.component.base.support.MethodInvokerExecutor;
import com.maishare.themis.component.param.ExecuteBeanCompParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Execute bean组件
 * @author moushaokun
 * @version @Id: ExecuteBeanComp.java, v 0.1 2020年03月11日 14:00 moushaokun Exp $
 */
public class DefaultExecuteBeanComp extends ExecuteComponent<ExecuteBeanCompParam> {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultExecuteBeanComp.class);
    
    @Override
    public void act(ThemisTestExecution themisTestExecution, ExecuteBeanCompParam executeCompParam) {
        MockMethodParamRequest mockMethodParamRequest = new MockMethodParamRequest();
        mockMethodParamRequest.setThemisTestExecution(themisTestExecution);
        mockMethodParamRequest.setHostName(executeCompParam.getBean());

        MockMethod methodMatch = MockFactory.getMockProcessor().getMatchMethod(mockMethodParamRequest).getMockMethod();

        Object hostBean = methodMatch.getHostBean();
    
        MethodInvokerExecutor.invoke(themisTestExecution,executeCompParam,hostBean);
    
        LOG.debug("DefaultExecuteBeanComp:普通环境下调用,class配置为具体实现类，参数：" + executeCompParam.toString());
    }

    @Override
    public String name() {
        return LibraCompNameConstants.CLAZZ;
    }
}

