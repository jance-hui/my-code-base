package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RootDao;
import entity.Root;
import service.RootService;

@Service
public class RootServiceImpl implements RootService{

	@Autowired
	RootDao rootDao;
	@Override
	public boolean loginSearch(Root root) {
		String rs = rootDao.loginSearch(root);
		if(rs != null) {
			return true;
		}
		return false;
	}

	@Override
	public Root searchByName(String username) {
		Root root = rootDao.searchByName(username);
		return root;
	}

}
