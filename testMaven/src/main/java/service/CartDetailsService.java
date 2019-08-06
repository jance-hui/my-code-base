package service;

import entity.CartDetails;

public interface CartDetailsService {

	public int searchNUM(CartDetails cartDetails);

	public boolean add(CartDetails cartDetails);

	public boolean del(CartDetails cartDetails);
	
	public boolean update(CartDetails cartDetails);
}
