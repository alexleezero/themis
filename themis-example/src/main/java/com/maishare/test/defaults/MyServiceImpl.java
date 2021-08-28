/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.test.defaults;

import org.bravo.gaia.commons.money.MultiCurrencyMoney;

/**
 * @author moushaokun
 * @version @Id: MyServiceImpl.java, v 0.1 2020年03月28日 16:32 moushaokun Exp $
 */
public class MyServiceImpl implements MyService {

    private MyMockService myMockService = new MyMockServiceImpl();
    
    private MessageService messageService = new MessageServiceImpl();

    @Override
    public long pay(String fromUserNo, String toUserNo) {
        MultiCurrencyMoney money = myMockService.getMoney();
        System.out.println(String.format("[%s]转账[%s]给[%s]", fromUserNo, money.getAmount().toString(), toUserNo));
    
        messageService.send("13568861795");
        
        return money.getCent();
    }
}

