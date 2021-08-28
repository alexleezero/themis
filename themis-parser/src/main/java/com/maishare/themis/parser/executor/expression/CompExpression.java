/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.expression;

import java.util.Map;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.context.component.LibraComponent;
import com.maishare.themis.context.component.LibraComponentParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.ExtensionLoader;
import com.maishare.themis.parser.executor.factory.LibraComponentParamFactory;
import com.maishare.themis.parser.libra.LibraParser;

/**
 * 组件表达式
 * @author moushaokun
 * @version @Id: CompExpression.java, v 0.1 2020年03月11日 18:02 moushaokun Exp $
 */
public class CompExpression extends AbstractExpression {

    private LibraParser.CompDeclContext context;

    public CompExpression(LibraParser.CompDeclContext context){
        this.context = context;
    }

    @Override
    public void evaluate(ThemisTestExecution themisTestExecution, Map<String, Object> params) {
        LibraCompType libraCompType = LibraCompType.getLibraCompType(context.compType().getText());

        if (libraCompType == null) {
            throw new ComponentException(String.format("不支持的组件类型：%s", context.compType().getText()));
        }

        //找到组件
        LibraComponent component = themisTestExecution.getThemisContext()
                .getLibraCompRegistry()
                .retrieve(libraCompType, context.compName().getText());

        if (component == null) {
            throw new ComponentException(String.format("未注册的组件类型：%s", context.compType().getText()));
        }

        //构造组件参数
        LibraComponentParam componentParam =
                ExtensionLoader.getExtensionLoader(LibraComponentParamFactory.class).getAdaptiveExtension()
                        .createParam(context, themisTestExecution, params);
        //运行组件
        component.act(themisTestExecution, componentParam);
    }

}

