package dao;

import java.util.List;

import entity.Orders;

public interface OrdersDao {

	public int add(Orders orders);
	public int returnNewID();
	
	public Orders searchByID(int id);
	
	public List<Orders> searchByUID(int id);
}
