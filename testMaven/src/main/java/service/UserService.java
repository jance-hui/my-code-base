package service;

import java.util.List;

import entity.User;

public interface UserService {

	public List<User> search() ;
	
	public User searchByName(String username) ;
	
	public boolean searchNew(String username) ;
	
	public boolean loginSearch(User user) ;
	
	public boolean update(User user) ;
	
	public int add(User user) ;
}
