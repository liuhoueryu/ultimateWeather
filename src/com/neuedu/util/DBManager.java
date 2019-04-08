package com.neuedu.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

	private static Properties prop = new Properties();

	private static String dbDriver;
	private static String dbUrl;
	private static String dbUsername;
	private static String dbPassword;

	// 初始化
	static {

		try {
			// 加载属性文件到内存
			prop.load(DBManager.class.getResourceAsStream("db-config.properties"));

			// 读取数据库的连接信息
			dbDriver = prop.getProperty("dbDriver");
			dbUrl = prop.getProperty("dbUrl");
			dbUsername = prop.getProperty("dbUsername");
			dbPassword = prop.getProperty("dbPassword");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Connection conn;
	private PreparedStatement pstmt;

	// 单例
	private static DBManager instance = new DBManager();

	public static DBManager getInstance() {
		return instance;
	}

	// 构造方法---加载数据库驱动
	private DBManager() {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 打开数据库连接
	private void openConnection() {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/haoren", dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 更新方法
	public boolean execUpdate(String sql, Object... params) {

		this.openConnection();

		try {
			pstmt = this.conn.prepareStatement(sql);

			// 参数赋值
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			this.closeConnection();
		}

	}

	// 查询方法
	public ResultSet execQuery(String sql, Object... params) {

		this.openConnection();

		try {
			pstmt = conn.prepareStatement(sql);

			// 参数赋值
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}

			return pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	// 关闭数据库连接
	public void closeConnection() {

		if (this.pstmt != null) {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		DBManager dbManager = DBManager.getInstance();

		String sql = "insert into user values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		
		ResultSet rs = dbManager.execQuery(sql,22, 222, 22, ' ',
				'男', ' ', ' ', ' ', ' ', '2010-02-02');

		try {
			while (rs.next()) {
				System.out.println(rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(4) + "-" + rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

	}

}
