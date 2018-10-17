package com.codefans.util.dbutil;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDB {

	private Connection conn;

	@Before
	public void before() {
		conn = DBUtil.getInstance().getConnection();
	}

	@Test
	public void test() {

		CompanyInfoBean bean = (CompanyInfoBean) load("select * from companyinfo", CompanyInfoBean.class);
		bean.print();
		// testConn();
	}

	public Object load(String sql, Class cls) {
		CompanyInfoBean bean = new CompanyInfoBean();
		Object obj = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			String[] arr = new String[colCount];
			String name = "";
			String type = "";
			p("colCount: " + colCount);
			// for(int i = 1; i <= colCount; i ++) {
			// name = metaData.getColumnName(i);
			// type = metaData.getColumnTypeName(i);
			// len = metaData.getPrecision(i);
			// p("name: " + name + ", type: " + type + ", precision: " + len);
			// }

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

			// obj = cls.newInstance();

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

	public void testConn() {
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			String sql = "select count(*) from companyinfo";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int num = 0;
			while (rs.next()) {
				num = rs.getInt(1);
			}
			p("num: " + num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void after() {

	}

	public void p(Object o) {
		System.out.println(o);
	}

}
