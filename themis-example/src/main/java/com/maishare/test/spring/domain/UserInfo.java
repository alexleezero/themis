/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.test.spring.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author hejianbing
 * @version @Id: UserInfo.java, v 0.1 2020年03月27日 09:40 hejianbing Exp $
 */
@Setter
@Getter
public class UserInfo implements Serializable {

    private String userName;

    private int    age;

    private String sex;

    private int    id;
}