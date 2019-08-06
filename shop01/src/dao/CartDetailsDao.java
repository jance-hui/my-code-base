package dao;

import entity.CartDetails;

public interface CartDetailsDao {
	
	public String searchNUM(CartDetails cartDetails);

	public int add(CartDetails cartDetails);
	
	public int del(CartDetails cartDetails);
	
	public int update(CartDetails cartDetails);
	
}
