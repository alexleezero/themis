/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.config.db;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.maishare.themis.config.ThemisProps;

import lombok.Getter;

/**
 * Themis数据源
 * @author moushaokun
 * @version @Id: ThemisDataSource.java, v 0.1 2020年03月21日 14:14 moushaokun Exp $
 */
public class ThemisDataSource {

    private static final String  DB_USER_NAME   = "db.userName";

    private static final String  DB_PASSWORD    = "db.password";

    private static final String  DB_URL_STRING  = "db.url";

    @Getter
    private DataSource dataSource;

    private ThemisDataSource(){
        if (ThemisProps.getProps() == null) {
           return;
        }

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(ThemisProps.getProps().getProperty(DB_URL_STRING));
        dataSource.setUsername(ThemisProps.getProps().getProperty(DB_USER_NAME));
        dataSource.setPassword(ThemisProps.getProps().getProperty(DB_PASSWORD));
        DruidAssembler druidProperties = new DruidAssembler(ThemisProps.getProps());
        druidProperties.assembleDruid(dataSource);

        this.dataSource = dataSource;
    }

    public static ThemisDataSource getInstance(){
        return ThemisDataSourceInstanceHelper.dataSource;
    }

    private static class ThemisDataSourceInstanceHelper {
        private static ThemisDataSource dataSource = new ThemisDataSource();
    }

}
