/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.test.defaults;


import org.bravo.gaia.commons.money.MultiCurrencyMoney;

/**
 * @author moushaokun
 * @version @Id: MyMockServiceImpl.java, v 0.1 2020年03月28日 16:38 moushaokun Exp $
 */
public class MyMockServiceImpl implements MyMockService {
    @Override
    public MultiCurrencyMoney getMoney() {
        return new MultiCurrencyMoney(8888, 156);
    }
}

