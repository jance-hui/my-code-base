package dao;

import java.util.List;

import entity.Cart;
import entity.CartDetails;

public interface CartDao {

	public int add(Cart cart);
	public int returnNewID();
	
	public int del(int id);

	public Cart searchCart(int id);
	
	public Cart searchByUID(int userID);
	
	public List<CartDetails> search(int id);
	
}
