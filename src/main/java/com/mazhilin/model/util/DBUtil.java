package com.mazhilin.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 資料庫連線工具類
 */
public class DBUtil {
	// 資料庫設定
	// final不可變動static存放到記憶體private私人無法在外面做呼叫
	private static final String URL = "jdbc:mysql://localhost:7777/practice_db";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("找不到驅動程式類別請檢查!" + e.getMessage());
		}
	}

	public static Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("連線資料庫失敗，請檢查設定！");
			e.printStackTrace();
			throw e;
		}
	}

}
