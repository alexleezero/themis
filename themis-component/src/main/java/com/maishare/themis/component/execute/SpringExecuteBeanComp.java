/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.execute;

import com.maishare.themis.common.constants.LibraCompNameConstants;
import com.maishare.themis.component.base.support.MethodInvokerExecutor;
import com.maishare.themis.component.param.ExecuteBeanCompParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**spring 环境下的bean执行操作
 * @author hejianbing
 * @version @Id: SpringExecuteBeanComp.java, v 0.1 2020年03月28日 10:54 hejianbing Exp $
 */
public class SpringExecuteBeanComp  extends ExecuteComponent<ExecuteBeanCompParam> {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultExecuteBeanComp.class);
	
	@Override
	public void act(ThemisTestExecution themisTestExecution, ExecuteBeanCompParam executeBeanCompParam) {
		
		Object bean = themisTestExecution.getThemisContext().getBean(executeBeanCompParam.getBean());
		
		MethodInvokerExecutor.invoke(themisTestExecution,executeBeanCompParam,bean);
		
		LOG.debug("DefaultExecuteBeanComp: spring环境下的bean调用,bean配置为接口类，参数：" + executeBeanCompParam.toString());
	}
	
	@Override
	public String name() {
		return LibraCompNameConstants.BEAN;
	}
}