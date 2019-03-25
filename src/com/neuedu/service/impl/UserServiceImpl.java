package com.neuedu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.neuedu.dao.UserDAO;
import com.neuedu.dao.impl.UserDAOImpl;
import com.neuedu.entity.User;
import com.neuedu.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImpl();   //���
	
	@Override
	public User login(String username, String password, int score) {
		
		User user = userDAO.findUser(username, password);   //ί��
		
		if(user!=null){
			
			if(userDAO.updateScore(user, score)){   //ί��
				
				//��ǰ����ӷ�
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
