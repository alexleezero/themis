/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.support;

import com.maishare.themis.common.constants.LibraSupportFileDataConstants;
import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.domain.MethodParam;
import com.maishare.themis.component.base.mock.MethodInvocation;
import com.maishare.themis.component.param.ExecuteBeanCompParam;
import com.maishare.themis.component.utils.MethodResolverUtils;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.context.execution.ThemisTestExecution;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * test case方法调用执行器
 * @author hejianbing
 * @version @Id: MethodInvokerExecutor.java, v 0.1 2020年03月19日 12:08 hejianbing Exp $
 */
public class MethodInvokerExecutor {
	
	
	public static <T> T invoke(ThemisTestExecution testExecution,
	                           ExecuteBeanCompParam executeCompParam,
	                           Object bean) {
		
		LibraFile libraFile = testExecution.getLibraFile();
        String methodName = executeCompParam.getMethod();
		
        Object data = testExecution.getLibraData().getData()
	        .get(LibraSupportFileDataConstants.PREPARE_DATA);
		try {
			
			MethodParam methodParam = new MethodParam();
			methodParam.setBean(bean);
			methodParam.setMethodName(methodName);
			methodParam.setThemisTestExecution(testExecution);
			methodParam.setData(data);
			
			MethodInvocation methodInvocation = MethodResolverUtils.resolveMethod(methodParam);
			
			Method method = methodInvocation.getMethod();
			
			T returnObject = (T) method.invoke(bean,methodInvocation.getArgs());
			
			postMethod(testExecution, method, returnObject);
			
			return returnObject;
			
		} catch (Exception e) {
			e.printStackTrace();
            throw new ComponentException(
                String.format("调用【%s】类方法【%s】异常",
	                libraFile.getClassName(), methodName));
		}
	}
	
    /**
     * 方法调用后处理
     */
    private static <T> void postMethod(ThemisTestExecution executionInstance,
                                       Method method,
                                       T returnedObject) {
    	
        if (method.getReturnType().getTypeName().equals(Void.class.getSimpleName().toLowerCase())) {
            return;
        }
		Map<String, Object> data = executionInstance.getLibraData().getData();
		data.put(LibraSupportFileDataConstants.EXECUTE_COMP_DATA, ObjectUtils.unwrapOptional(returnedObject));

    }
	
}