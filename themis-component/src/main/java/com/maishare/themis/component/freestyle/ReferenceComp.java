/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.freestyle;

import com.maishare.themis.common.constants.LibraCompNameConstants;
import com.maishare.themis.common.exception.ThemisException;
import com.maishare.themis.component.param.ReferenceCompParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.context.testcase.ThemisTestCase;

/**
 * Reference 组件，使逻辑可以相互依赖
 * @author moushaokun
 * @version @Id: ReferenceComp.java, v 0.1 2020年03月11日 14:16 moushaokun Exp $
 */
public class ReferenceComp extends FreeStyleComponent<ReferenceCompParam> {

    @Override
    public void act(ThemisTestExecution themisTestExecution, ReferenceCompParam param) {
        ThemisTestCase themisTestCase = themisTestExecution.getThemisContext().getTestCases().get(param.getDataIndex());
        if(themisTestCase == null){
            throw new ThemisException(String.format("不存在的用例[%s]", param.getDataIndex()));
        }
        themisTestCase.execute();
    }

    @Override
    public String name() {
        return LibraCompNameConstants.REFERENCE;
    }
}

