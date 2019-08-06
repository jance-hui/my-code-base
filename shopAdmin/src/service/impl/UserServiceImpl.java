package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import entity.User;
import service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> search() {
		List<User> userList = userDao.search();
		return userList;
	}

	@Override
	public User searchByName(String username) {
		User user = userDao.searchByName(username);
		return user;
	}
	
	@Override
	public int searchCount() {
		int count = userDao.searchCount();
		return count;
	}

	@Override
	public User searchByID(int id) {
		User user = userDao.searchByID(id);
		return user;
	}

	@Override
	public boolean add(User user) {
		int rs = userDao.add(user);
		return rs>0;
	}

	@Override
	public List<User> searchByUser(User user) {
		List<User> users = userDao.searchByUser(user);
		return users;
	}

}
