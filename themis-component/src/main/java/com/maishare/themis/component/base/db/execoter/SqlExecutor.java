/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.execoter;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.maishare.themis.common.exception.ComponentException;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.Getter;

/**
 * Sql 执行器
 * @author moushaokun
 * @version @Id: SqlExecutor.java, v 0.1 2020年03月23日 14:10 moushaokun Exp $
 */
public class SqlExecutor {

    @Getter
    private JdbcTemplate       jdbcTemplate;

    private static SqlExecutor executor;

    private SqlExecutor(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static SqlExecutor getInstance(DataSource dataSource) {
        if (executor == null) {
            synchronized (SqlExecutor.class) {
                if (executor == null) {
                    if (dataSource == null) {
                        throw new ComponentException("使用DB组件请先配置数据源");
                    }
                    executor = new SqlExecutor(dataSource);
                }
            }
        }
        return executor;
    }

    public Integer update(String sql, Object... params){
        return getJdbcTemplate().update(sql, params);
    }

    public List<Map<String,Object>> queryForListMap(String sql, Object... args){
        return getJdbcTemplate().queryForList(sql, args);
    }


}
