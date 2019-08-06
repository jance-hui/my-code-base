package service;

import java.util.List;

import entity.Cart;
import entity.CartDetails;

public interface CartService {

	public int add(Cart cart);
	
	public boolean del(int id);

	public Cart searchCart(int id);
	
	public Cart searchByUID(int userID);
	
	public List<CartDetails> search(int id);
}
