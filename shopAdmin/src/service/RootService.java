package service;

import entity.Root;

public interface RootService {
	
	public boolean loginSearch(Root root);
	
	public Root searchByName(String username);
}
