package com.neuedu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.neuedu.dao.UserDAO;
import com.neuedu.dao.impl.UserDAOImpl;
import com.neuedu.entity.User;
import com.neuedu.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImpl();   //组合
	@Override
	public User retrieveUserPassword(String username, String password) {
		
		return userDAO.updatePassword(username, password);
	}
	@Override
	public boolean retrievePassword(String username, String phonenumber) {
		boolean user=userDAO.findUserNumber(username, phonenumber);
		return user;
	}
	@Override
	public User login(String username, String password, int score) {
		
		User user = userDAO.findUser(username, password);   //委托
		
		if(user!=null){
			
			if(userDAO.updateScore(user, score)){   //委托
				
				//当前对象加分
				user.setScore(user.getScore() + score);
				
				return user;
			}
		}
		
		return null;
	}

	@Override
	public User register(User user) {
		
		if(userDAO.insertUser(user)){
			
			return userDAO.findUser(user.getUsername(), user.getPassword());
			
		}
		
		return null;
	}

	@Override
	public List<User> getUserList(String username, String gender, String job, String begin, String end) {
		return userDAO.findUserList(username, gender, job, begin, end);
	}

	@Override
	public boolean checkUsername(String username) {
		return userDAO.findUser(username);
	}

	@Override
	public boolean deleteUser(int userid) {
		return userDAO.deleteUser(userid);
	}

	@Override
	public boolean deleteUser(String[] userids) {
		
		/*for(String userid : userids){
			if(!userDAO.deleteUser(Integer.parseInt(userid))){
				return false;
			}
		}
		
		return true;*/
		
		return userDAO.deleteUser(userids);
		
	}

}
