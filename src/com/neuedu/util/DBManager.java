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

	// ��ʼ��
	static {

		try {
			// ���������ļ����ڴ�
			prop.load(DBManager.class.getResourceAsStream("db-config.properties"));

			// ��ȡ���ݿ��������Ϣ
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

	// ����
	private static DBManager instance = new DBManager();

	public static DBManager getInstance() {
		return instance;
	}

	// ���췽��---�������ݿ�����
	private DBManager() {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// �����ݿ�����
	private void openConnection() {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/haoren", dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ���·���
	public boolean execUpdate(String sql, Object... params) {

		this.openConnection();

		try {
			pstmt = this.conn.prepareStatement(sql);

			// ������ֵ
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

	// ��ѯ����
	public ResultSet execQuery(String sql, Object... params) {

		this.openConnection();

		try {
			pstmt = conn.prepareStatement(sql);

			// ������ֵ
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}

			return pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	// �ر����ݿ�����
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

		String sql = "select * from weather where city like ? and province like ? order by province asc, city asc,date desc";

		String city="����";
		String province="ɽ��";
		ResultSet rs = dbManager.execQuery(sql,city,province);

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
