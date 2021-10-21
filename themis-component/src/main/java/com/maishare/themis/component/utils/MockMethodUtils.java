/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.utils;

import com.maishare.themis.component.base.mock.MockMethod;
import com.maishare.themis.component.base.mock.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.aop.support.AopUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * mock数据处理工具类
 * @author hejianbing
 * @version @Id: MockMethodMatcherUtils.java, v 0.1 2020年03月27日 17:38 hejianbing Exp $
 */
public final class MockMethodUtils {
    public static boolean clazzMatch(InvocationOnMock invocation, MockMethod defData){
		return invocation.getClass().equals(defData.getTargetBean().getClass()) ||
			AopUtils.getTargetClass(defData.getTargetBean()).isAssignableFrom(invocation.getMock().getClass());
    }

	public static boolean methodMatch(InvocationOnMock invocation,MethodInvocation methodInvocation ){
        if (methodInvocation == null) {
            return false;
        }
		
	    boolean methodMatch =  invocation.getMethod().getName().equals(methodInvocation.getMethod().getName());
	
	   List<MethodInvocation.Parameter> availableParameters = methodInvocation.getParameters().stream()
		    .filter(param->param.getValue() != null && StringUtils.isNotBlank(param.getValue().toString()))
		    .collect(Collectors.toList());
	    
	    boolean noneArgs = invocation.getArguments().length == 0 || availableParameters.size() == 0;
	    
        if (noneArgs && methodMatch) {
            return true;
        }
	
	    boolean paramSize = invocation.getArguments().length
		    == methodInvocation.getParameters().size();
	
	    //参数类型匹配
	    boolean argTypeMatch = false;
	
	    if(paramSize){
		    argTypeMatch =  argTypeMatch(invocation,methodInvocation);
	    }
	    
	    return methodMatch && paramSize && argTypeMatch;
    }

	public static boolean argTypeMatch(InvocationOnMock invocationOnMock,MethodInvocation methodInvocation) {
		Class<?>[] parameterTypes = invocationOnMock.getMethod().getParameterTypes();
		
		Object[] arguments = invocationOnMock.getArguments();
		
		boolean argTypeMatch = false;
		for (int i = 0; i < methodInvocation.getParameters().size(); i++) {
			MethodInvocation.Parameter parameter = methodInvocation.getParameters().get(i);
			if (parameter.getValue() == null) {
				continue;
			}
			Object argument = arguments[i];
			if (null != argument && !"".equals(argument)
				&& !parameter.getParameterType().isAssignableFrom(argument.getClass())) {
				
				argTypeMatch = true;
				break;
			} else {
				argTypeMatch = parameter.getParameterType().isAssignableFrom(parameterTypes[i]);
			}
			if (!argTypeMatch) {
				break;
			}
		}
		return argTypeMatch;
	}
	
}