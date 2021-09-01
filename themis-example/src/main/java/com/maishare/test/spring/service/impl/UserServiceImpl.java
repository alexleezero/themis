/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.test.spring.service.impl;

import com.maishare.test.spring.domain.UserInfo;
import com.maishare.test.spring.service.AddressService;
import com.maishare.test.spring.service.UserService;
import com.oppo.gaia.commons.domain.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hejianbing
 * @version @Id: UserServiceImpl.java, v 0.1 2020年03月25日 20:43 hejianbing Exp $
 */
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private AddressService addressService;
	
	@Override
	public String notify(List<String> userNames, String age) {
		
		System.out.println("userName="+userNames+", age="+age);

		addressService.add();
		return "xx";
	}
	
	@Override
	public void resetPassword(String userName, String password) {
		System.out.println("userName="+userName+",password="+password);
	}
	
	@Override
	public List<UserInfo> findOneUser(String id) {
		return null;
	}
	
	@Override
	public void updateById(UserInfo userInfo) {
	
	}
	
	@Override
	public BaseResult<UserInfo> getById(String id) {
		return null;
	}
}