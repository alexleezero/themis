package com.maishare.themis.component.base.mock;

import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.checker.AssertMatcherChain;
import com.maishare.themis.component.base.checker.MatcherContext;
import com.maishare.themis.component.base.convert.TypeConverterExecutor;
import com.maishare.themis.component.enums.MockParamType;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.ExtensionLoader;
import org.apache.commons.collections.CollectionUtils;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mockito default answer to be used by mock when not stubbed
 * @author hejianbing
 * @version @Id: MockMethodInterceptorAnswer.java, v 0.1 2020年03月24日 09:21 hejianbing Exp $
 */
public class MockMethodInterceptorAnswer implements Answer<Object>, ValidableAnswer, Serializable {

    private static final AssertMatcherChain    assertMatcherChain = ExtensionLoader
        .getExtensionLoader(AssertMatcherChain.class).getAdaptiveExtension();

    private static final TypeConverterExecutor converterExecutor  = TypeConverterExecutor.getInstance();

    public ThemisTestExecution          themisTestExecution;
    
    public MockMethodInterceptorAnswer(ThemisTestExecution themisTestExecution){
        this.themisTestExecution = themisTestExecution;
    }
 

    @Override
    public Object answer(InvocationOnMock invocation) throws Throwable {
        MockMethodParamRequest mockMethodParamRequest = new MockMethodParamRequest();
        mockMethodParamRequest.setInvocationOnMock(invocation);
        mockMethodParamRequest.setThemisTestExecution(themisTestExecution);

        MockMethodInvokeParam request =  MockFactory.getMockProcessor()
                .getMatchMethod(mockMethodParamRequest);

        if (request.getMethodInvocation() != null) {
            MockMethod methodMatcher = request.getMockMethod();
            Object result = methodMatcher.getResult();
            MockParamType paramType = methodMatcher.getParamType();

            MethodInvocation methodInvocation = request.getMethodInvocation();

            if (paramType == MockParamType.PARAM_TYPE) {
                parameterTypeMatch(methodInvocation, invocation);
            }
            if (paramType == MockParamType.PARAM) {
                assertMatch(invocation, methodInvocation);
            }
            Class<?> returnType = invocation.getMethod().getReturnType();
            if (!returnType.getTypeName().equals(Void.class.getSimpleName().toLowerCase())) {
                if(result != null){
                    return converterExecutor.convert(result, returnType);
                }
            }
            return null;
        }
        return invocation.callRealMethod();
    }

    @Override
    public void validateFor(InvocationOnMock invocation) {
    }

    private void assertMatch(InvocationOnMock invocation, MethodInvocation methodInvocation) {
        List<MethodInvocation.Parameter> matchParameter = methodInvocation.getParameters().stream()
            .filter(param -> null != param.getValue()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(matchParameter)) {
            return;
        }
        for (MethodInvocation.Parameter parameter : matchParameter) {
            Object reqParam = invocation.getArguments()[parameter.getIndex()];

            if (reqParam == null) {
                throw new ComponentException("mock parameter value mismatch");
            }
            MatcherContext matcherContext = new MatcherContext();
            matcherContext.setActual(reqParam);
            matcherContext.setExpected(parameter.getValue());
            assertMatcherChain.match(matcherContext);
        }
    }

    private void parameterTypeMatch(MethodInvocation methodInvocation, InvocationOnMock invocation) {
        List<MethodInvocation.Parameter> matchParameter = methodInvocation.getParameters();
        if (CollectionUtils.isEmpty(matchParameter)) {
            return;
        }
        for (MethodInvocation.Parameter parameter : matchParameter) {
            Object paramValue = parameter.getValue();
            if(paramValue == null){
                continue;
            }
            
            boolean noneParamTypeMatch = invocation.getArguments()[parameter.getIndex()] == null
                || !((Class) paramValue).isAssignableFrom(invocation.getArguments()[parameter.getIndex()].getClass());
            
            
            if (noneParamTypeMatch) {
                throw new ComponentException("mock parameter type mismatch");
            }
        }
    }

}