package dao;

import java.util.List;

import entity.User;

public interface UserDao {

	public List<User> search() ;
	
	public User searchByName(String username) ;
	
	public int searchNew(String username) ;
	
	public String loginSearch(User user) ;
	
	public int update(User user) ;
	
	public int add(User user) ;
	public int returnNewId();
}
