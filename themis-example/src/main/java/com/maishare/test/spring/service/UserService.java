/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.test.spring.service;

import com.maishare.test.spring.domain.UserInfo;
import com.oppo.gaia.commons.domain.BaseResult;

import java.util.List;

/**
 * @author hejianbing
 * @version @Id: UserService.java, v 0.1 2020年03月25日 20:42 hejianbing Exp $
 */
public interface UserService {

	String notify(List<String> userNames,String age);

	void resetPassword(String userName,String password);

	List<UserInfo> findOneUser(String id);


	void updateById(UserInfo userInfo);

	BaseResult<UserInfo> getById(String id);
	
	
}