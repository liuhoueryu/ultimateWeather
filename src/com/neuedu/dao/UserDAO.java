package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.User;

public interface UserDAO {

	User findUser(String username, String password);
	
	boolean updateScore(User user, int score);
	User updatePassword(String username,String password);
	
	boolean insertUser(User user);
	
	List<User> findUserList(String username, String gender, String job, String begin, String end);
	
	boolean findUser(String username);
	boolean findUserNumber(String username,String phonenumber);
	
	boolean deleteUser(int userid);
	
	boolean deleteUser(String[] userids);
	
}
