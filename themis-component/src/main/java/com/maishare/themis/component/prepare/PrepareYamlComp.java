/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.prepare;

import com.maishare.themis.common.constants.LibraCompNameConstants;
import com.maishare.themis.common.constants.LibraSupportFileDataConstants;
import com.maishare.themis.component.base.func.LibraSupportFileContentWrapper;
import com.maishare.themis.component.base.support.LibraSupportFileContentContext;
import com.maishare.themis.component.base.support.LibraSupportFileContentWrapperFactory;
import com.maishare.themis.component.param.CommonIndexCompParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Prepare yaml组件
 * @author moushaokun
 * @version @Id: PrepareYamlComp.java, v 0.1 2020年03月11日 13:54 moushaokun Exp $
 */
public class PrepareYamlComp extends PrepareComponent<CommonIndexCompParam> {
    

    public static final Logger LOG = LoggerFactory.getLogger(PrepareYamlComp.class);

    @Override
    public void act(ThemisTestExecution themisTestExecution, CommonIndexCompParam commonIndexCompParam) {
        LibraSupportFileContentContext libraFileDataContext =
            LibraSupportFileContentContext.newInstance(commonIndexCompParam.getIndex(), commonIndexCompParam.getFileName())
            .setDataFileList(themisTestExecution.getLibraFile().getLibraPrepareFiles());
       
        LibraSupportFileContentWrapper libraSupportFileContentWrapper = LibraSupportFileContentWrapperFactory
            .getInstance(libraFileDataContext);
    
        // 文件内容数据包装转换器
        Map<String, Object> dataItem = libraSupportFileContentWrapper.wrap(libraFileDataContext);

        if (MapUtils.isNotEmpty(dataItem)) {

            themisTestExecution.getLibraData().getData().put(LibraSupportFileDataConstants.PREPARE_DATA,
                dataItem.get(libraFileDataContext.getDataKey()));
            LOG.debug("PrepareYamlComp: 通过yaml准备memory数据。。。，参数：" + commonIndexCompParam.toString());
            return;
        }
        
        LOG.debug("PrepareYamlComp: 通过yaml准备memory数据,没有找到准备参数");
    }
    
    
    @Override
    public String name() {
        return LibraCompNameConstants.YAML;
    }
}

