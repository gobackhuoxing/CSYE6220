package edu.neu.csye6220.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.web.dao.UserDao;
import edu.neu.csye6220.web.domain.User;

@Service("usersService")
public class UsersService {
	
	private UserDao userDao;
	
	@Autowired
	public void userDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void create(User user) {
		userDao.create(user);
		
	}

	public boolean exists(String username) {
		return userDao.exists(username);
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}
	
	public User getUser(String username) {
		if(username == null) return null;
		List<User> users = userDao.getUsers(username);
		if(users.size()==0) return null;
		return users.get(0);
	}

	public void manage(boolean enabled, String authority, String username) {
		userDao.manage(enabled,authority,username);
	}

}