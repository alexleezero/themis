/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.mock;

import com.google.common.collect.Lists;
import com.maishare.themis.common.constants.LibraSupportFileDataConstants;
import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.data.PrepareMockData;
import com.maishare.themis.component.base.domain.MethodParam;
import com.maishare.themis.component.base.mock.MethodInvocation;
import com.maishare.themis.component.base.mock.MockContext;
import com.maishare.themis.component.base.mock.MockFactory;
import com.maishare.themis.component.base.support.LibraSupportFileContentContext;
import com.maishare.themis.component.base.support.LibraSupportFileContentWrapperFactory;
import com.maishare.themis.component.enums.MockParamType;
import com.maishare.themis.component.param.CommonIndexCompParam;
import com.maishare.themis.component.utils.MethodResolverUtils;
import com.maishare.themis.component.utils.MockDataValid;
import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.utils.ReflectUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * mock操作默认实现
 * @author hejianbing
 * @version @Id: AbstractMockComp.java, v 0.1 2020年03月23日 17:17 hejianbing Exp $
 */
public abstract class AbstractMockComp extends MockComponent<CommonIndexCompParam> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractMockComp.class);

    @Override
    public void act(ThemisTestExecution themisTestExecution,
                    CommonIndexCompParam commonIndexCompParam) {
        // 准备数据
        List<PrepareMockData> prepareMockDataList = doPreparedMock(themisTestExecution,
            commonIndexCompParam);

        if (CollectionUtils.isNotEmpty(prepareMockDataList)) {

            // 初始始化 mock
            List<MockContext> mockContextList = doExecuteMock(themisTestExecution,
                prepareMockDataList);

            doProcessInjectMock(mockContextList,themisTestExecution);

            LOG.debug("MockComp: 从文件中获取并mock数据，参数：" + commonIndexCompParam.toString());
        }
    }

    protected void doProcessInjectMock(List<MockContext> mockContexts,ThemisTestExecution themisTestExecution) {
        for (MockContext mockContext : mockContexts) {
            Object hostBean = getHostBean(mockContexts, mockContext.getHostBean());

            Field field = ReflectUtils.findField(hostBean.getClass(), f -> f.getType().isAssignableFrom(mockContext.getTargetObject().getClass()));

            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, hostBean, mockContext.getProxyObject());
        }
    }

    private Object getHostBean(List<MockContext> mockContexts, Object hostBean) {
        for (MockContext mockContext : mockContexts) {
            String beanName = mockContext.getPrepareMockData().getBean();
            try {
                Class<?> clazz = Class.forName(beanName);
                if (clazz.isAssignableFrom(hostBean.getClass())) {
                    return mockContext.getProxyObject();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return hostBean;
    }

    /**
     * 	初始始化 mock
     */
    private List<MockContext> doExecuteMock(ThemisTestExecution themisTestExecution,
                                            List<PrepareMockData> prepareMockDataList) {

        List<MockContext> mockContextList = Lists.newArrayList();

        for (PrepareMockData prepareMockData : prepareMockDataList) {
            String beanName = prepareMockData.getBean();

            ThemisContext context = themisTestExecution.getThemisContext();
            Object bean = context.getBean(beanName);
            if(null==prepareMockData.getHostBean()){
                throw new ComponentException("mock "+bean.getClass().getName()+" object not config hostBean");
            }
            Object hostBean = context.getBean(prepareMockData.getHostBean());

            String method = prepareMockData.getMethod();

            Object data = getMethodParamData(prepareMockData);

            // 组装方法解析参数
            MethodParam methodParam = new MethodParam();
            methodParam.setMethodName(method);
            methodParam.setBean(bean);
            methodParam.setData(data);
            methodParam.setMockParamType(prepareMockData.getMockParamType());
            methodParam.setThemisTestExecution(themisTestExecution);

            //解析方法信息
            MethodInvocation methodInvocation = MethodResolverUtils.resolveMethod(methodParam);
            // 组装mock参数
            MockContext mockitoContext = new MockContext();
            mockitoContext.setHostBean(hostBean);
            mockitoContext.setMethodInvocation(methodInvocation);
            mockitoContext.setThemisTestExecution(themisTestExecution);
            mockitoContext.setPrepareMockData(prepareMockData);
            mockitoContext.setTargetObject(bean);

            //创建mock对象
            MockFactory.getMockProcessor().createMock(mockitoContext);

            mockContextList.add(mockitoContext);
        }
        return mockContextList;
    }

    private Object getMethodParamData(PrepareMockData prepareMockData) {
        Object param = prepareMockData.getParam();

        Object paramType = prepareMockData.getParamType();

        if (param != null) {
            return param;
        }
        if (paramType != null) {
            return paramType;
        }
        return null;
    }

    /**
     * 组装数据
     */
    protected List<PrepareMockData> doPreparedMock(ThemisTestExecution themisTestExecution,
                                                   CommonIndexCompParam commonIndexCompParam) {
        LibraSupportFileContentContext libraFileDataContext = LibraSupportFileContentContext
            .newInstance(commonIndexCompParam.getIndex(), commonIndexCompParam.getFileName())
            .setDataFileList(themisTestExecution.getLibraFile().getLibraMockFiles());

        Map<String, Object> dataItem = LibraSupportFileContentWrapperFactory
            .getInstance(libraFileDataContext).wrap(libraFileDataContext);

        Object data = dataItem.get(LibraSupportFileDataConstants.DATA);

        List<PrepareMockData> prepareMockDataList = Lists.newArrayList();

        if (data instanceof List) {
            List dataList = List.class.cast(data);
            for (Object mockDataItem : dataList) {
                PrepareMockData prepareMockData = mapToData(Map.class.cast(mockDataItem));

                prepareMockDataList.add(prepareMockData);
            }
        } else if (data instanceof Map) {
            PrepareMockData prepareMockData = mapToData(data);
            prepareMockDataList.add(prepareMockData);

        } else {
            throw new ComponentException("mock data def error");
        }
        return prepareMockDataList;
    }

    private PrepareMockData mapToData(Object data) {
        Map<String, Object> dataMap = Map.class.cast(data);

        MockDataValid.checkNotNull(dataMap);

        String bean = String.class.cast(dataMap.get(LibraSupportFileDataConstants.BEAN));
        String hostBean = String.class.cast(dataMap.get(LibraSupportFileDataConstants.HOST_BEAN));
        String method = String.class.cast(dataMap.get(LibraSupportFileDataConstants.METHOD));

        Object paramType = dataMap.get(LibraSupportFileDataConstants.PARAM_TYPE);
        Object param = dataMap.get(LibraSupportFileDataConstants.PARAM);
        Object returnData = dataMap.get(LibraSupportFileDataConstants.RETURN_DATA);

        PrepareMockData prepareMockData = new PrepareMockData();
        prepareMockData.setBean(bean);
        prepareMockData.setHostBean(hostBean);
        prepareMockData.setMethod(method);
        prepareMockData.setParamType(paramType);
        prepareMockData.setParam(param);
        prepareMockData.setRet(returnData);

        setParamType(prepareMockData);
        return prepareMockData;
    }

    private static void setParamType(PrepareMockData prepareMockData) {
        Object param = prepareMockData.getParam();

        Object paramType = prepareMockData.getParamType();
        if (paramType != null) {
            prepareMockData.setMockParamType(MockParamType.PARAM_TYPE);
        }
        if (param != null) {
            prepareMockData.setMockParamType(MockParamType.PARAM);
        }

    }

}