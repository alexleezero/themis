package com.maishare.test.spring;

import org.springframework.context.ApplicationEvent;

public class AddAddressEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AddAddressEvent(Object source) {
        super(source);
    }
}
