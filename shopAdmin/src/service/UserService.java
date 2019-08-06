package service;

import java.util.List;

import entity.User;

public interface UserService {

	public List<User> search() ;
	
	public User searchByName(String username) ;
	
	public int searchCount() ;

	public User searchByID(int id);

	public boolean add(User user);

	public List<User> searchByUser(User user);
}
