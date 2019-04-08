package com.neuedu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.UserDAO;
import com.neuedu.entity.User;
import com.neuedu.util.DBManager;

public class UserDAOImpl implements UserDAO {

	private DBManager dbManager = DBManager.getInstance();

	@Override
	public User updatePassword(String username,String password) {
		String sql = "update user set password = ? where username = ?";
		dbManager.execUpdate(sql, password,username);
		return null;
	}

	@Override
	public boolean findUserNumber(String username, String phonenumber) {
		String sql = "select * from user where username = ? and phonenumber = ?";
		ResultSet rs = dbManager.execQuery(sql, username, phonenumber);
		try {
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findUser(String username, String password) {

		String sql = "select * from user where username = ? and password = ?";

		ResultSet rs = dbManager.execQuery(sql, username, password);

		try {

			if (rs.next()) { // ��¼�ɹ�

				// ȡ���û����
				int userid = rs.getInt(1);

				// ȡ������
				int score = rs.getInt(4);

				// ȡ��ͷ��
				String photo = rs.getString(5);

				// ���������user����
				User user = new User();

				user.setUserid(userid);
				user.setUsername(username);
				user.setScore(score);
				user.setPhoto(photo);

				return user;

			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			dbManager.closeConnection();
		}

		return null;
	}

	@Override
	public boolean updateScore(User user, int score) {

		String sql = "update user set score = score + ? where userid = ?";

		return dbManager.execUpdate(sql, score, user.getUserid());

	}

	@Override
	public boolean insertUser(User user) {

		String sql = "insert into user values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		return dbManager.execUpdate(sql, user.getUsername(), user.getPassword(), user.getScore(), user.getPhoto(),
				user.getGender(), user.getJob(), user.getInterest(), user.getRegtime(), user.getPhonenumber(), user.getBirthday());

	}

	@Override
	public List<User> findUserList(String username, String gender, String job, String begin, String end) {

		String sql = null;

		ResultSet rs = null;

		if ("".equals(begin) && "".equals(end)) { // ȫ��ʱ��
			sql = "select * from user where username like ? and gender like ? and job like ?";
			rs = dbManager.execQuery(sql, "%" + username + "%", "%" + gender + "%", "%" + job + "%");
		} else {
			sql = "select * from user where username like ? and gender like ? and job like ? and regtime between ? and ?";

			rs = dbManager.execQuery(sql, "%" + username + "%", "%" + gender + "%", "%" + job + "%",
					begin + " 00:00:00", end + " 23:59:59");
		}

		List<User> list = new ArrayList<>();

		try {
			while (rs.next()) {

				User user = new User();

				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setScore(rs.getInt(4));
				user.setPhoto(rs.getString(5));
				user.setGender(rs.getString(6));
				user.setJob(rs.getString(7));
				user.setRegtime(rs.getTimestamp(9));

				list.add(user);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

		return null;
	}

	@Override
	public boolean findUser(String username) {

		String sql = "select * from user where username = ?";

		ResultSet rs = dbManager.execQuery(sql, username);

		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

		return false;
	}

	@Override
	public boolean deleteUser(int userid) {

		String sql = "delete from user where userid = ?";

		return dbManager.execUpdate(sql, userid);
	}

	@Override
	public boolean deleteUser(String[] userids) {

		String sql = "delete from user where userid in (";

		StringBuilder sb = new StringBuilder(sql);

		for (String userid : userids) {
			sb.append("?,");
		}

		sb.deleteCharAt(sb.length() - 1).append(")");

		sql = sb.toString();

		return dbManager.execUpdate(sql, userids);
	}

}
