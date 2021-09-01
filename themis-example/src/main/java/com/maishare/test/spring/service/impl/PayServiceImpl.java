/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.test.spring.service.impl;

import com.maishare.test.spring.AddAddressEvent;
import com.maishare.test.spring.domain.UserInfo;
import com.maishare.test.spring.service.PayService;
import com.maishare.test.spring.service.UserService;
import com.oppo.gaia.commons.domain.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author hejianbing
 * @version @Id: PayServiceImpl.java, v 0.1 2020年03月12日 13:44 hejianbing Exp $
 */
@Component
public class PayServiceImpl implements PayService {

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationContext application;
	
	@Override
	public BaseResult<List<UserInfo>> pay(List<String> userNames, String age) {
		application.publishEvent(new AddAddressEvent("test"));
		
		List<UserInfo> userInfoList = userService.findOneUser("1");
		
		userService.notify(Arrays.asList("zs","ls"),"23");
		
		BaseResult<List<UserInfo>> baseResult = new BaseResult<>();
		baseResult.setSuccess(true);
		baseResult.setResultObj(userInfoList);

		return baseResult;
	}
	
	@Override
	public void refund() {
	
	}
	
	@Override
	public void replay() {
	
	}
	
}