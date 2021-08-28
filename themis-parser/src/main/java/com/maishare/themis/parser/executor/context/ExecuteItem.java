/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.context;

import java.util.Map;

import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.executor.expression.Expression;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 执行项
 * @author moushaokun
 * @version @Id: ExecuteItem.java, v 0.1 2020年03月16日 15:26 moushaokun Exp $
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteItem {

    private Expression          expression;

    private ThemisTestExecution execution;

    private Map<String, Object> params;
}
