/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Libra领域模型
 * @author lijian
 * @version @Id: Libra.java, v 0.1 2020年03月03日 09:35 lijian Exp $
 */
@Getter
@Setter
public class Libra {

    /** libra资源文件 */
    private LibraFile           libraFile;

    /** libra实例 */
    private List<LibraInstance> libraInstanceList = new ArrayList<>();


}
