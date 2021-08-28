package com.maishare.test.spring.service;

import com.maishare.test.spring.AddAddressEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AddressListener implements ApplicationListener<AddAddressEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(AddAddressEvent event) {

        System.out.println("-============");

    }
}
