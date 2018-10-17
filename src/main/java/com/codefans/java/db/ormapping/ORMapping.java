package com.codefans.java.db.ormapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ORMapping {

	private PreparedStatement pstmt = null;
	private Connection conn = null;
	private ResultSet rs = null;
	private int TOTAL_TABLES = 21;

	private String url = "";
	private String username = "";
	private String password = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ORMapping orMapping = new ORMapping();
		orMapping.map();
	}

	public void map() {
		initDB();
		dbToObj();
	}

	public void initDB() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			url = "jdbc:mysql://10.0.0.123:3306/globaldb";
			username = "msol";
			password = "msol";
			conn = DriverManager.getConnection(url, username, password);
			// String sql = "select * from universalidmap_1 where value='' and
			// appended=0";
			String sql = "select * from universalidmap_1 where appended = 1 limit 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dbToObj() {
		try {

			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();
			System.out.println(columnCount);

			Object obj = this.reflect(rs, UniversalidMap.class);

			ReflectionToStringBuilder toStringBuilder = new ReflectionToStringBuilder(obj,
					ToStringStyle.SHORT_PREFIX_STYLE);
			System.out.println(toStringBuilder.toString());

			// for(int i = 0; i < columnCount; i ++) {
			// rsMetaData.getc
			// }

			rs.last();
			System.out.println(rs.getRow());
			rs.first();

			int rowCount = 0;
			while (rs.next()) {
				rowCount++;
				System.out.println(rowCount);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object reflect(ResultSet rs, Class<?> cls) {
		Object obj = null;
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();

			Field[] fields = cls.getFields();

			if (fields != null) {
				Field field = null;
				String fieldName = "";
				Class fieldType = null;
				Method method = null;
				String fieldValue = "";
				String methodName = "";
				obj = cls.newInstance();

				rs.next();

				for (int i = 0; i < fields.length; i++) {
					field = fields[i];
					fieldName = field.getName();
					fieldType = field.getType();
					if (fieldType == Byte.class) {
						fieldValue = String.valueOf(rs.getByte(fieldName));
						methodName = this.generateMethodName(fieldName);
						method = cls.getMethod(methodName, Byte.class);
						method.invoke(obj, Byte.parseByte(fieldValue));
						System.out.println("Byte");
					}
					// if(fieldType == char.class) {
					// or this way below
					if (fieldType.getName().equals("char")) {
						fieldValue = String.valueOf(rs.getString(fieldName));
						methodName = this.generateMethodName(fieldName);
						method = cls.getMethod(methodName, char.class);
						method.invoke(obj, fieldValue.charAt(0));
						System.out.println("Character");
					}
					// if(fieldType.getName().equals("int")) {
					// or this way below
					if (fieldType == int.class) {
						fieldValue = String.valueOf(rs.getInt(fieldName));
						methodName = this.generateMethodName(fieldName);
						method = cls.getMethod(methodName, int.class);
						method.invoke(obj, Integer.parseInt(fieldValue));
						System.out.println("Integer");
					}
					if (fieldType == boolean.class) {
						fieldValue = String.valueOf(rs.getBoolean(fieldName));
						methodName = this.generateMethodName(fieldName);
						method = cls.getMethod(methodName, boolean.class);
						method.invoke(obj, Boolean.parseBoolean(fieldValue));
						System.out.println("Boolean");
					}
					if (fieldType == short.class) {
						fieldValue = String.valueOf(rs.getShort(fieldName));
						methodName = this.generateMethodName(fieldName);
						method = cls.getMethod(methodName, short.class);
						method.invoke(obj, Short.parseShort(fieldValue));
						System.out.println("Short");
					}
					if (fieldType == long.class) {
						fieldValue = String.valueOf(rs.getLong(fieldName));
						methodName = this.generateMethodName(fieldName);
						method = cls.getMethod(methodName, long.class);
						method.invoke(obj, Long.parseLong(fieldValue));
						System.out.println("Long");
					}
					if (fieldType == float.class) {
						fieldValue = String.valueOf(rs.getFloat(fieldName));
						methodName = this.generateMethodName(fieldName);
						method = cls.getMethod(methodName, float.class);
						method.invoke(obj, Float.parseFloat(fieldValue));
						System.out.println("Float");
					}
					if (fieldType == double.class) {
						fieldValue = String.valueOf(rs.getDouble(fieldName));
						methodName = this.generateMethodName(fieldName);
						method = cls.getMethod(methodName, double.class);
						method.invoke(obj, Double.parseDouble(fieldValue));
						System.out.println("Double");
					}
					if (fieldType == String.class) {
						fieldValue = String.valueOf(rs.getString(fieldName));
						methodName = this.generateMethodName(fieldName);
						method = cls.getMethod(methodName, String.class);
						method.invoke(obj, fieldValue);
						System.out.println("String");
					}

				}
			}

			// ReflectionToStringBuilder toStringBuilder = new
			// ReflectionToStringBuilder(obj, ToStringStyle.SHORT_PREFIX_STYLE);
			// System.out.println(toStringBuilder.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;

	}

	public String generateMethodName(String fieldName) {
		StringBuffer sb = new StringBuffer();
		sb.append("set").append(String.valueOf(fieldName.charAt(0)).toUpperCase()).append(fieldName.substring(1));
		return sb.toString();
	}

}
