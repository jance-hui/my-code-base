package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartDetailsDao;
import entity.CartDetails;
import service.CartDetailsService;

@Service
public class CartDetailsServiceImpl implements CartDetailsService{

	@Autowired
	CartDetailsDao cartDetailsDao;
	
	@Override
	public int searchNUM(CartDetails cartDetails) {
		String rs = cartDetailsDao.searchNUM(cartDetails);
		if(rs == null) {
			return 0;
		}else {
			return Integer.parseInt(rs);
		}
	};
	@Override
	public boolean add(CartDetails cartDetails) {
		int rs = cartDetailsDao.add(cartDetails);
		return rs>0;
	}

	@Override
	public boolean del(CartDetails cartDetails) {
		int rs = cartDetailsDao.del(cartDetails);
		return rs>0;
	}

	@Override
	public boolean update(CartDetails cartDetails) {
		int rs = cartDetailsDao.update(cartDetails);
		return rs>0;
	}

}
