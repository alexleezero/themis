/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.config;

import com.maishare.themis.config.db.ThemisDataSource;
import com.maishare.themis.config.redis.ThemisRedisConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.connection.RedisConfiguration;

import javax.sql.DataSource;

/**
 * themis主配置类
 * @author lijian
 * @version @Id: ThemisConfig.java, v 0.1 2020年03月02日 15:17 lijian Exp $
 */
@Setter
@Getter
public class ThemisConfig {

    /** libra test case 文件根目录 **/
    private String             testLibraFileLocation = "testcase";


    /** 数据源配置 */
    private DataSource         dataSource            = ThemisDataSource.getInstance().getDataSource();

    /** redis配置 */
    private RedisConfiguration redisConfiguration    = ThemisRedisConfiguration.getInstance().getConfig();
}
