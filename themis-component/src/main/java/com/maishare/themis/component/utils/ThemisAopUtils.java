/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.utils;

import org.springframework.aop.framework.Advised;
import org.springframework.util.Assert;

/**
 * Aop拦截器工具类
 * @author hejianbing
 * @version @Id: AopUtils.java, v 0.1 2020年03月24日 17:06 hejianbing Exp $
 */
public class ThemisAopUtils {
	public static <T> T getUltimateTargetObject(Object candidate) {
		Assert.notNull(candidate, "Candidate must not be null");
		try {
			if (org.springframework.aop.support.AopUtils.isAopProxy(candidate) && candidate instanceof Advised) {
				Object target = ((Advised) candidate).getTargetSource().getTarget();
				if (target != null) {
					return (T) getUltimateTargetObject(target);
				}
			}
		}
		catch (Throwable ex) {
			throw new IllegalStateException("Failed to unwrap proxied object", ex);
		}
		return (T) candidate;
	}
}