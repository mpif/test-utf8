package com.codefans.util.dbutil;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DBUtil {

	ApplicationContext ctx;
	private JdbcTemplate jdbcTemplate;
	private Connection conn;

	private DBUtil() {
		ctx = new FileSystemXmlApplicationContext("src/com/messagesolution/util/dbutil/spring-cfg.xml");

		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");

		BasicDataSource resource = (BasicDataSource) ctx.getBean("dataSource");
		try {
			conn = DataSourceUtils.doGetConnection(resource);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static DBUtil dbUtil;

	public static DBUtil getInstance() {
		if (dbUtil == null) {
			return new DBUtil();
		}
		return dbUtil;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public Connection getConnection() {
		return conn;
	}

	public Object load(String sql, Class cls) {
		Object obj = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			String name = "";
			String type = "";

			Method[] mths = cls.getDeclaredMethods();
			Method method = null;
			String mthName = "";
			rs.next();
			obj = cls.newInstance();
			for (int i = 1; i <= colCount; i++) {
				name = metaData.getColumnName(i);
				type = metaData.getColumnTypeName(i);
				for (int j = 0; j < mths.length; j++) {
					method = mths[j];
					mthName = method.getName();
					if (mthName.equalsIgnoreCase(this.toMethodName(name, "set"))) {
						if (type.equals("INT")) {
							method.invoke(obj, rs.getInt(i));
						} else if (type.equals("VARCHAR")) {
							method.invoke(obj, rs.getString(i));
						}
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

	public String toMethodName(String field, String type) {
		StringBuilder sb = new StringBuilder();
		String temp = field.substring(1);
		String c = String.valueOf(field.charAt(0));
		c = c.toUpperCase();
		sb.append(type);
		sb.append(c);
		sb.append(temp);
		return sb.toString();
	}

}
