package com.oppo.gaia.commons.domain;

import lombok.Data;

import javax.print.attribute.standard.PrinterURI;

/**
 * @author lijian
 * @since 2021/8/30
 */
@Data
public class BaseResult<T> {

    private T resultObj;
    private boolean success;
}
