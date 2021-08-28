package com.maishare.themis.component.base.mock;

import com.maishare.themis.context.execution.ThemisTestExecution;
import lombok.Getter;
import lombok.Setter;
import org.mockito.invocation.InvocationOnMock;

@Setter
@Getter
public class MockMethodParamRequest {

    private String              targetName;

    private String              hostName;

    private InvocationOnMock    invocationOnMock;

    private ThemisTestExecution themisTestExecution;
}
