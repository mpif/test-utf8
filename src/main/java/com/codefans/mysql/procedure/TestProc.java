package com.codefans.mysql.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class TestProc {
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/msdb?user=msol&password=msol";
		conn = DriverManager.getConnection(url);
		CallableStatement cstmt = conn.prepareCall("{call insertFolder(?, ?, ?, ?, ?)}");
		// cstmt.registerOutParameter(3, Types.INTEGER);
		// cstmt.registerOutParameter(4, Types.INTEGER);

		int userid = 1249;
		String name = "垃圾邮件##";
		String path = "/%E5%9E%83%E5%9C%BE%E9%82%AE%E4%BB%B6/";
		String serverIp = "exchange.com";
		int ext6 = 1;

		cstmt.setInt(1, userid);
		cstmt.setString(2, name);
		cstmt.setString(3, path);
		cstmt.setString(4, serverIp);
		cstmt.setInt(5, ext6);

		cstmt.execute();
		// System.out.println(cstmt.getInt(3));
		// System.out.println(cstmt.getInt(4));
		cstmt.close();
		conn.close();
	}
}
