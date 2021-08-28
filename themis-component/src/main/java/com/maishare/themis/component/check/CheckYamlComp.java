/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.check;

import com.maishare.themis.common.constants.LibraCompNameConstants;
import com.maishare.themis.common.constants.LibraSupportFileDataConstants;
import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.checker.AssertMatcherExecutor;
import com.maishare.themis.component.base.checker.MatcherContext;
import com.maishare.themis.component.base.domain.CompareOperator;
import com.maishare.themis.component.base.support.LibraSupportFileContentContext;
import com.maishare.themis.component.base.support.LibraSupportFileContentWrapperFactory;
import com.maishare.themis.component.param.CommonIndexCompParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.ExtensionLoader;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Check yaml 组件，用yaml中的数据检查内存中的数据
 * @author moushaokun
 * @version @Id: CheckYamlComp.java, v 0.1 2020年03月11日 14:07 moushaokun Exp $
 */
public class CheckYamlComp extends CheckComponent<CommonIndexCompParam> {
    
    private static final Logger LOG = LoggerFactory.getLogger(CheckYamlComp.class);
    
    private AssertMatcherExecutor matcherExecutor = ExtensionLoader
        .getExtensionLoader(AssertMatcherExecutor.class).getAdaptiveExtension();


    @Override
    public void act(ThemisTestExecution themisTestExecution, CommonIndexCompParam commonIndexCompParam) {
        Object result = themisTestExecution.getLibraData().getData().get(LibraSupportFileDataConstants.EXECUTE_COMP_DATA);

        if (result != null) {
            Pair<CompareOperator,Object> pair = getPrepareData(themisTestExecution, commonIndexCompParam);
    
            MatcherContext matcherContext = new MatcherContext();
            matcherContext.setExpected(pair.getRight());
            matcherContext.setActual(result);
            matcherContext.setCompareOperator(pair.getLeft());

            matcherExecutor.match(matcherContext);
        }
    
        LOG.debug("检查yaml数据是否和内存中的运算数据相匹配，参数：" + commonIndexCompParam.toString());
    }
    
  
    
    @Override
    public String name() {
        return LibraCompNameConstants.YAML;
    }
    
    
    /**
     * 获取期望值
     */
    private Pair<CompareOperator,Object> getPrepareData(ThemisTestExecution themisTestExecution,
                                  CommonIndexCompParam commonIndexCompParam) {
        
        LibraSupportFileContentContext dataContextBuilder = LibraSupportFileContentContext
            .newInstance(commonIndexCompParam.getIndex(), commonIndexCompParam.getFileName())
            .setDataFileList(themisTestExecution.getLibraFile().getLibraCheckFiles());
    
        //文件内容包装数据转换
        Map<String,Object> dataItem = LibraSupportFileContentWrapperFactory.getInstance(dataContextBuilder)
            .wrap(dataContextBuilder);
    
        Object data = dataItem.get(dataContextBuilder.getDataKey());
       
        if (null == data) {
            throw new ComponentException(String.format("check配置文件缺少data配置数据项内容,文件【%s】,索引【%s】",
                commonIndexCompParam.getFileName(), commonIndexCompParam.getIndex()));
        }
        Object opt = dataItem.get(LibraSupportFileDataConstants.OPT);
        CompareOperator compareOperator = CompareOperator.EQUAL;
        
        if (opt != null) {
            compareOperator = CompareOperator.get(opt.toString());
        }
        return Pair.of(compareOperator, data);
    }
}

