package dao;

import java.util.List;

import entity.User;

public interface UserDao {

	public List<User> search() ;
	
	public User searchByName(String username) ;
	
	public int searchCount() ;
	
	public User searchByID(int id);
	
	public int add(User user);
	
	public List<User> searchByUser(User user);
}
