package com.maishare.themis.component.base.mock;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MockMethodInvokeParam {

    private MockMethod       mockMethod;

    private MethodInvocation methodInvocation;
}
