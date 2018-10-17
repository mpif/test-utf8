package com.codefans.mysql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ConnTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// connTest();
		testUpdate();
	}

	public static void connTest() {
		String url = "jdbc:mysql://localhost:3306/msdb";
		String username = "msol";
		String password = "msol";
		String sql = "insert into catalogdetail(catalogid, messageid, receivedate, holdDate) values(?, ?, ?, ?)";

		String driverClassName = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			int begin = 11;
			int end = begin + 2;
			for (int i = begin; i < end; i++) {
				pstmt.setInt(1, i);
				pstmt.setString(2, "111_" + i);
				pstmt.setString(3, "111_" + i);
				pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				int n = pstmt.executeUpdate();
				if (n > 0) {
					System.out.println("插入成功, n=[" + n + "]");
				} else {
					System.out.println("插入失败, n=[" + n + "]");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {
		String url = "jdbc:mysql://localhost:3306/msdb";
		String username = "msol";
		String password = "msol";
		String sql = "delete from catalogdetail";

		String driverClassName = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// int n = pstmt.executeUpdate();
			// if(n > 0) {
			boolean n = pstmt.execute();
			if (n) {
				System.out.println("更新成功, n=[" + n + "]");
			} else {
				System.out.println("更新失败, n=[" + n + "]");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
