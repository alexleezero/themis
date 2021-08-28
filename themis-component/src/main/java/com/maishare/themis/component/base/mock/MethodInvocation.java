/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.mock;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方法信息
 * @author hejianbing
 * @version @Id: MethodInvocation.java, v 0.1 2020年03月24日 09:21 hejianbing Exp $
 */
@Setter
@Getter
public class MethodInvocation {

    private Method          method;

    private Object          bean;

    private Class<?>        returnType;

    private List<Parameter> parameters = new ArrayList<>();

    public Object[] getArgs() {
        Object[] argValues = parameters.stream().map(param -> param.getValue())
            .collect(Collectors.toList()).toArray();

        return argValues;
    }

    @Setter
    @Getter
    public static class Parameter {
        private String   parameterName;

        private Class<?> parameterType;

        private Object   value;

        private int      index;
        
    }
}