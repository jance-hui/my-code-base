package dao;

import entity.Root;

public interface RootDao {
	
	public Root searchByName(String username) ;
	
	public String loginSearch(Root root) ;

}
