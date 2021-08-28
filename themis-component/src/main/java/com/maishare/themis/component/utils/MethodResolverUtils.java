/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.utils;

import com.maishare.themis.common.constants.LibraSupportFileDataConstants;
import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.convert.TypeConverterExecutor;
import com.maishare.themis.component.base.domain.MethodParam;
import com.maishare.themis.component.base.mock.MockMethod;
import com.maishare.themis.component.base.mock.MethodInvocation;
import com.maishare.themis.component.enums.MockParamType;
import com.maishare.themis.context.execution.ThemisTestExecution;
import org.apache.commons.lang3.ClassUtils;
import org.mockito.Mockito;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.convert.TypeDescriptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 方法调用工具类
 * @author hejianbing
 * @version @Id: MethodResolverUtils.java, v 0.1 2020年03月24日 09:26 hejianbing Exp $
 */
public final class MethodResolverUtils {
   
    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
   
    public static final  TypeConverterExecutor typeConverterService = TypeConverterExecutor.getInstance();
    
    public static MethodInvocation resolveMethod(MethodParam methodParam){
        Object bean = methodParam.getBean();
    
        Object data = methodParam.getData();
    
        String methodName = methodParam.getMethodName();
    
        MockParamType paramType = methodParam.getMockParamType();
    
        ThemisTestExecution testExecution = methodParam.getThemisTestExecution();
    
        MethodInvocation methodInvocation = new MethodInvocation();
        methodInvocation.setBean(bean);
        int argLength = argLength(data);
    
        Method method = getMethod(bean, methodName, argLength);
    
        Class<?>[] parameterTypes = method.getParameterTypes();
    
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
    
        if (null == targetClass) {
            targetClass = bean.getClass();
        }
        Method targetMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        String[] paramNames = parameterNameDiscoverer.getParameterNames(targetMethod);
    
        if (Mockito.mockingDetails(bean).isMock() && null != testExecution) {
            paramNames = getTargetMethodParamNames(bean, method, testExecution);
        }
        if (null != paramNames && paramNames.length > 0) {
            for (int i = 0; i <= paramNames.length - 1; i++) {
                String parameterName = paramNames[i];
                Class<?> parameterType = parameterTypes[i];
            
                MethodInvocation.Parameter parameter = new MethodInvocation.Parameter();
                parameter.setParameterName(parameterName);
                parameter.setParameterType(parameterType);
                parameter.setIndex(i);
            
                methodInvocation.getParameters().add(parameter);
            }
        }
        methodInvocation.setMethod(method);
    
        Class<?> returnType = method.getReturnType();
    
        methodInvocation.setReturnType(returnType);
        
        if (paramType == MockParamType.PARAM && argLength > 0) {
            resolveArgument(methodInvocation, data);
        }
        
        if (paramType == MockParamType.PARAM_TYPE && argLength > 0) {
            resolveArgumentType(methodInvocation, data);
        }
        
        return methodInvocation;
    
    }
    
    private static void resolveArgumentType(MethodInvocation methodInvocation,Object data) {
        if (data instanceof List) {
            List paramTypeList = List.class.cast(data);
        
            for (int i = 0; i < paramTypeList.size(); i++) {
                Object paramTypeValue = paramTypeList.get(i);
            
                MethodInvocation.Parameter parameter = methodInvocation.getParameters().get(i);
                parameter.setValue(getParamType(paramTypeValue));
            }
            return;
        }
        
        if(data instanceof Map){
            Map<String,String> paramNames = Map.class.cast(data);
            List<MethodInvocation.Parameter> parameters = methodInvocation.getParameters();
    
            for(Map.Entry<String, String> entry : paramNames.entrySet()){
                String paramTypeName = entry.getKey();
                
                boolean isMatch = false;
                
                for (MethodInvocation.Parameter parameter : parameters) {
                    if (parameter.getParameterName().equals(paramTypeName)) {
                        parameter.setValue(getParamType(entry.getValue()));
                        isMatch = true;
                    }
                }
                if(!isMatch){
                    throw new ComponentException("paramType name mismatch");
                }
            }
        } else{
            methodInvocation.getParameters().get(0).setValue(getParamType(data));
        }
    
    }
    
    private static Class<?> getParamType(Object paramTypeObj){
        try {
            Class<?> clazz = ClassUtils.getClass(paramTypeObj.toString());
            
            return clazz;
        } catch (ClassNotFoundException e) {
            throw new ComponentException("mock param mismatch");
        }
    }
    
    
    private static Method getMethod(Object bean, String methodName, int argLength) {
        Method[] methods = bean.getClass().getMethods();
        
        for (Method method : methods) {
            boolean nameMatch = method.getName().equals(methodName);
            boolean argsLen = method.getParameterTypes().length == argLength;
            if ((argsLen && nameMatch) || nameMatch) {
                return method;
            }
        }
        throw new ComponentException(
            String.format("未找到匹配的方法【%s】,【%s】", bean.getClass().getName(), methodName));
    }
    
    private static String[] getTargetMethodParamNames(Object mockObject, Method method,
                                                      ThemisTestExecution themisTestExecution) {
        List<MockMethod> mockDefDataList = List.class.cast(themisTestExecution.getLibraData()
            .getData().get(LibraSupportFileDataConstants.MOCK_CACHE_DATA));

        List<String> paramNames = new ArrayList<>();

        for (MockMethod mockDefData : mockDefDataList) {
            if (mockDefData.getProxy().equals(mockObject)) {
                MethodInvocation methodInvocation = mockDefData.getMethodInvocation();
                if (methodMatch(methodInvocation, method)) {
                    paramNames = methodInvocation.getParameters().stream()
                        .map(param -> param.getParameterName()).collect(Collectors.toList());
                    break;
                }
            }
        }
        return paramNames.toArray(new String[paramNames.size()]);
    }
    
    private static boolean methodMatch(MethodInvocation methodInvocation, Method method) {
        if (methodInvocation == null) {
            return false;
        }
        boolean methodNameMatch = methodInvocation.getMethod().getName() == method.getName() ;
        boolean paramLenMatch = methodInvocation.getParameters().size() == method.getParameterTypes().length;
        
        Class<?>[] targetParameterTypes = method.getParameterTypes();
        Class<?>[] parameterTypes = methodInvocation.getMethod().getParameterTypes();

        boolean paramTypeMatch = true;
        for (int i = 0; i < targetParameterTypes.length; i++) {
            if (targetParameterTypes[i] != parameterTypes[i]) {
                paramTypeMatch = false;
            }
        }
        return paramTypeMatch && methodNameMatch && paramLenMatch;
    }
    
    private static void resolveArgument(MethodInvocation methodInvocation, Object data) {
        for (MethodInvocation.Parameter methodParameter : methodInvocation.getParameters()) {
            if(data instanceof Map){
                Map<String, Object> paramItem = HashMap.class.cast(data);
                Object paramValue = paramItem.get(methodParameter.getParameterName());
            
                if (paramValue != null) {
                    setParamAndTypeConvert(methodInvocation.getMethod(), methodParameter, paramValue);
                }
            }else{
                setParamAndTypeConvert(methodInvocation.getMethod(), methodParameter, data);
            }
        }
    }
    
    private static void setParamAndTypeConvert(Method method, MethodInvocation.Parameter methodParameter,
                                               Object data) {
        try {
            if(!TypeDescriptor.forObject(data).getObjectType().isInstance(data)){
                throw new ComponentException("mock parameter values​​need to set the particular type");
            }
            Object val = typeConverterService.convert(data, methodParameter.getParameterType());
            methodParameter.setValue(val);
        } catch (Exception e) {
            throw new ComponentException(String.format("方法调用参转换错误【%s】:%s", method.getName(),e.getMessage()));
        }

    }
    
    /**
     * 获取参数个数
     */
    private static int argLength(Object data){
        if(data == null){
            return 0;
        }
        int paramSize = 1;
        if (data instanceof Map) {
            paramSize = Map.class.cast(data).values().size();
        }
        if(data instanceof List){
            paramSize = List.class.cast(data).size();
        }
        return paramSize;
    }
	
   
}