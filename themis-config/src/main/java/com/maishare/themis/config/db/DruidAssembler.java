package com.maishare.themis.config.db;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.MergeStatFilter;
import com.alibaba.druid.pool.DruidDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * druid assembler
 * @author lijian
 *
 */
public class DruidAssembler {
	private static final String DEFAULT_INITIAL_SIZE = "1";
	private static final String DEFAULT_MIN_IDLE = "1";
	private static final String DEFAULT_MAX_ACTIVE = "100";
	private static final String DEFAULT_MAX_WAIT = "60000";
	private static final String DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS = "60000";
	private static final String DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS = "300000";
	private static final String DEFAULT_VALIDATION_QUERY = "SELECT 'x'";
	private static final String DEFAULT_TEST_WHILE_IDLE = "true";
	private static final String DEFAULT_TEST_ON_BORROW = "false";
	private static final String DEFAULT_TEST_ON_RETURN = "false";
	private static final String DEFAULT_POOL_PREPARED_STATEMENTS = "false";
	private static final String DEFAULT_MAX_POOL_PREPARED_STATEMENT_PER_CONNECTION_SIZE = "20";
	private static final String DEFAULT_REMOVE_ABANDONED = "true";
	private static final String DEFAULT_REMOVE_ABANDONED_TIMEOUT = "1800";
	private static final String INITIAL_SIZE = "initialSize";
	private static final String MIN_IDLE = "minIdle";
	private static final String MAX_ACTIVE = "maxActive";
	private static final String MAX_WAIT = "maxWait";
	private static final String TIME_BETWEEN_EVICTION_RUNS_MILLIS = "timeBetweenEvictionRunsMillis";
	private static final String MIN_EVICTABLE_IDLE_TIME_MILLIS = "minEvictableIdleTimeMillis";
	private static final String VALIDATION_QUERY = "validationQuery";
	private static final String TEST_WHILE_IDLE = "testWhileIdle";
	private static final String TEST_ON_BORROW = "testOnBorrow";
	private static final String TEST_ON_RETURN = "testOnReturn";
	private static final String POOL_PREPARED_STATEMENTS = "poolPreparedStatements";
	private static final String MAX_POOL_PREPARED_STATEMENT_PER_CONNECTION_SIZE = "maxPoolPreparedStatementPerConnectionSize";
	private static final String REMOVE_ABANDONED ="removeAbandoned";
	private static final String REMOVE_ABANDONED_TIMEOUT ="removeAbandonedTimeout";
	private static final int DEFAULT_SLOW_SQL_MLS = 6000;
	private static final int DEFAULT_SCHEDULE_FLUSH_LOG_MLS = 300000;
	
	
	private final Properties props;
	
	public DruidAssembler(Properties props){
		this.props = props;
	}
	
	public void assembleDruid(DruidDataSource dataSource) {
		int initialSize = Integer.parseInt(props.getProperty(INITIAL_SIZE, DEFAULT_INITIAL_SIZE));
		int minIdle = Integer.parseInt(props.getProperty(MIN_IDLE, DEFAULT_MIN_IDLE));
		int maxActive = Integer.parseInt(props.getProperty(MAX_ACTIVE, DEFAULT_MAX_ACTIVE));
		int maxWait = Integer.parseInt(props.getProperty(MAX_WAIT, DEFAULT_MAX_WAIT));
		int timeBetweenEvictionRunsMillis = Integer.parseInt(props.getProperty(TIME_BETWEEN_EVICTION_RUNS_MILLIS, DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS));
		int minEvictableIdleTimeMillis = Integer.parseInt(props.getProperty(MIN_EVICTABLE_IDLE_TIME_MILLIS, DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS));
		String validationQuery = props.getProperty(VALIDATION_QUERY, DEFAULT_VALIDATION_QUERY);
		boolean testWhileIdle = Boolean.parseBoolean(props.getProperty(TEST_WHILE_IDLE, DEFAULT_TEST_WHILE_IDLE));
		boolean testOnBorrow = Boolean.parseBoolean(props.getProperty(TEST_ON_BORROW, DEFAULT_TEST_ON_BORROW));
		boolean testOnReturn = Boolean.parseBoolean(props.getProperty(TEST_ON_RETURN, DEFAULT_TEST_ON_RETURN));
		boolean poolPreparedStatements = Boolean.parseBoolean(props.getProperty(POOL_PREPARED_STATEMENTS, DEFAULT_POOL_PREPARED_STATEMENTS));
		int maxPoolPreparedStatementPerConnectionSize = Integer.parseInt(
				props.getProperty(MAX_POOL_PREPARED_STATEMENT_PER_CONNECTION_SIZE,
						DEFAULT_MAX_POOL_PREPARED_STATEMENT_PER_CONNECTION_SIZE));
		boolean removeAbandoned = Boolean.parseBoolean(props.getProperty(REMOVE_ABANDONED, DEFAULT_REMOVE_ABANDONED));
		int removeAbandonedTimeout = Integer.parseInt(props.getProperty(REMOVE_ABANDONED_TIMEOUT, DEFAULT_REMOVE_ABANDONED_TIMEOUT));

		dataSource.setRemoveAbandoned(removeAbandoned);
		dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
		dataSource.setInitialSize(initialSize);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxActive(maxActive);
		dataSource.setMaxWait(maxWait);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestWhileIdle(testWhileIdle);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(testOnReturn);
		dataSource.setPoolPreparedStatements(poolPreparedStatements);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		//config druid filter
		Slf4jLogFilter logFilter = new Slf4jLogFilter();
		MergeStatFilter  statFilter = new MergeStatFilter();
		//record slow sql
		statFilter.setSlowSqlMillis(DEFAULT_SLOW_SQL_MLS);
		statFilter.setLogSlowSql(true);
		logFilter.setStatementExecutableSqlLogEnable(true);
		List<Filter> filters = new ArrayList<>();
		filters.add(logFilter);
		filters.add(statFilter);
		dataSource.setProxyFilters(filters);
		//schedule flush the stat to log every 30s
		dataSource.setTimeBetweenLogStatsMillis(DEFAULT_SCHEDULE_FLUSH_LOG_MLS);
	}
	
	
	
}
