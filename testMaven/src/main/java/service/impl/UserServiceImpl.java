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
	public boolean searchNew(String username) {
		int rs = userDao.searchNew(username);
		return rs>0;
	}

	@Override
	public boolean loginSearch(User user) {
		String rs = userDao.loginSearch(user);
		if(rs != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(User user) {
		int rs = userDao.update(user);
		return rs>0;
	}

	@Override
	public int add(User user) {
		int rs = userDao.add(user);
		if(rs>0) {
			rs = userDao.returnNewId();
		}
		return rs;
	}

}
