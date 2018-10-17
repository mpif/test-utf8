package com.codefans.spring.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBUtil {

	private DBUtil() {
	};

	private static DBUtil dbUtil;

	public static DBUtil getInstance() {
		if (dbUtil == null) {
			return new DBUtil();
		}
		return dbUtil;
	}

	public JdbcTemplate getJdbcTemplate() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"src/com/messagesolution/spring/db/spring-cfg.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");

		return jdbcTemplate;
	}
}
