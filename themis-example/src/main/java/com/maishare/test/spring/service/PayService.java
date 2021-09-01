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
 * @version @Id: PayService.java, v 0.1 2020年03月12日 13:43 hejianbing Exp $
 */
public interface PayService {
    
    BaseResult<List<UserInfo>> pay(List<String> userNames, String age);
    
    void refund();
    
    void replay();

}