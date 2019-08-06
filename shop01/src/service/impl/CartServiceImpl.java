package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartDao;
import entity.Cart;
import entity.CartDetails;
import service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartDao cartDao;

	@Override
	public int add(Cart cart) {
		int rs = cartDao.add(cart);
		if(rs>0) {
			rs = cartDao.returnNewID();
		}
		return rs;
	}

	@Override
	public boolean del(int id) {
		int rs = cartDao.del(id);
		return rs>0;
	}

	@Override
	public List<CartDetails> search(int id) {
		List<CartDetails> list = cartDao.search(id);
		return list;
	}

	@Override
	public Cart searchCart(int id) {
		Cart cart = cartDao.searchCart(id);
		return cart;
	}

	@Override
	public Cart searchByUID(int userID) {
		Cart cart = cartDao.searchByUID(userID);
		return cart;
	}

}
