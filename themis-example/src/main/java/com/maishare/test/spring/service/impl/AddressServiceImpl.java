package com.maishare.test.spring.service.impl;

import com.maishare.test.spring.service.AddressService;
import org.springframework.stereotype.Component;

@Component
public class AddressServiceImpl implements AddressService {
    @Override
    public void add() {
        System.out.println("===add address==");
    }
}
