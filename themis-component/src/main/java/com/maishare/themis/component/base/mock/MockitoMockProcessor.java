/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.mock;

import com.google.common.collect.Lists;
import com.maishare.themis.common.constants.LibraSupportFileDataConstants;
import com.maishare.themis.component.base.data.PrepareMockData;
import com.maishare.themis.component.utils.MockMethodUtils;
import com.maishare.themis.component.utils.ThemisAopUtils;
import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.utils.ReflectUtils;
import org.apache.commons.lang3.StringUtils;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.test.mock.mockito.MockReset;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Mockito mock处理器
 * @author hejianbing
 * @version @Id: MockitoMockProcessor.java, v 0.1 2020年03月25日 13:52 hejianbing Exp $
 */
public class MockitoMockProcessor implements MockProcessor {

    @Override
    public <T> T createMock(MockContext mockitoContext) {
        MockSettings settings = MockReset.withSettings(MockReset.AFTER);
        Object bean = mockitoContext.getMethodInvocation().getBean();
        if (AopUtils.isAopProxy(bean)) {
            bean = ThemisAopUtils.getUltimateTargetObject(bean);
        }
        settings.spiedInstance(bean);
        settings.defaultAnswer(new MockMethodInterceptorAnswer(mockitoContext.getThemisTestExecution()));
        String beanName = bean.getClass().getName();
        MockMethodParamRequest mockMethodParamRequest = new MockMethodParamRequest();
        mockMethodParamRequest.setThemisTestExecution(mockitoContext.getThemisTestExecution());
        mockMethodParamRequest.setTargetName(beanName);
        MockMethodInvokeParam mockMethodParamMatcher = this.getMatchMethod(mockMethodParamRequest);
        MockMethod mockMethod = mockMethodParamMatcher.getMockMethod();

        Object proxyObject = getMockObject(settings, bean, mockMethod);
        mockitoContext.setProxyObject(proxyObject);

        this.addMockMethod(mockitoContext);
        return (T) proxyObject;
    }



    @Override
    public void resetMock(List<MockMethod> mockMethods, ThemisContext themisContext) {
        for (MockMethod mockMethod : mockMethods) {
            Object hostBean = mockMethod.getHostBean();

            Field field = ReflectUtils.findField(hostBean.getClass(), f -> f.getType().isAssignableFrom(mockMethod.getTargetBean().getClass()));

            Object targetBean = mockMethod.getTargetBean();

            ReflectUtils.setField(field,hostBean,targetBean);
        }

    }

    @Override
    public MockMethodInvokeParam getMatchMethod(MockMethodParamRequest request) {
        Object methodMatcherData = request.getThemisTestExecution().getLibraData().getData()
                .get(LibraSupportFileDataConstants.MOCK_CACHE_DATA);

        MockMethodInvokeParam result = new MockMethodInvokeParam();
        if (methodMatcherData == null) {
            return result;
        }
        List<MockMethod> methodMatchers = List.class.cast(methodMatcherData);

        MockMethod mockMethod = getMockMethod(request, methodMatchers);
        if (null != mockMethod) {
            result.setMockMethod(mockMethod);
            return result;
        }
        InvocationOnMock invocationOnMock = request.getInvocationOnMock();
        if (null != invocationOnMock) {
            for (MockMethod methodMatcher : methodMatchers) {
                if (MockMethodUtils.clazzMatch(invocationOnMock, methodMatcher)) {
                    result.setMockMethod(methodMatcher);

                    MethodInvocation methodInvocation = methodMatcher.getMethodInvocation();

                    if (MockMethodUtils.methodMatch(invocationOnMock, methodInvocation)) {
                        result.setMethodInvocation(methodInvocation);
                        break;
                    }
                }
            }
        }
        return result;
    }

    private MockMethod getMockMethod(MockMethodParamRequest request, List<MockMethod> methodMatchers) {

        for (MockMethod methodMatcher : methodMatchers) {
            if (StringUtils.isNotBlank(request.getHostName()) && request.getHostName()
                .equals(methodMatcher.getHostBean().getClass().getName())) {
                return methodMatcher;
            }
            if (StringUtils.isNotBlank(request.getTargetName()) && request.getTargetName()
                .equals(methodMatcher.getTargetBean().getClass().getName())) {
                return methodMatcher;
            }
        }
        return null;
    }

    private void addMockMethod(MockContext mockitoContext) {
        ThemisTestExecution themisTestExecution = mockitoContext.getThemisTestExecution();
        MethodInvocation methodInvocation = mockitoContext.getMethodInvocation();
        PrepareMockData prepareMockData = mockitoContext.getPrepareMockData();

        MockMethod mockMethodMatcher = new MockMethod();
        mockMethodMatcher.setProxy(mockitoContext.getProxyObject());
        mockMethodMatcher.setHostBean(mockitoContext.getHostBean());
        mockMethodMatcher.setMethodInvocation(methodInvocation);
        mockMethodMatcher.setTargetBean(methodInvocation.getBean());
        mockMethodMatcher.setResult(prepareMockData.getRet());
        mockMethodMatcher.setParamType(prepareMockData.getMockParamType());

        Object data = themisTestExecution.getLibraData().getData()
            .get(LibraSupportFileDataConstants.MOCK_CACHE_DATA);
        if (data == null) {
            List<MockMethod> mockMethodMatcherList = Lists.newArrayList();

            mockMethodMatcherList.add(mockMethodMatcher);

            themisTestExecution.getLibraData().getData()
                .put(LibraSupportFileDataConstants.MOCK_CACHE_DATA, mockMethodMatcherList);
        } else {
            List<MockMethod> mockMethodMatcherList = (ArrayList) data;
            mockMethodMatcherList.add(mockMethodMatcher);
        }
    }

    private Object getMockObject(MockSettings settings, Object bean, MockMethod mockMethod) {
        Object proxyObject;
        if (mockMethod == null) {
            proxyObject = Mockito.mock(bean.getClass(), settings);
        } else {
            proxyObject = mockMethod.getProxy();
        }
        return proxyObject;
    }
}