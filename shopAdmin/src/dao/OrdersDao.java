package dao;

import java.util.List;

import entity.Orders;

public interface OrdersDao {

	public Orders searchByID(int id);
	
	public List<Orders> searchByUID(int id);
	
	public int searchCount();

	public List<Float> searchMoney();
	
	public List<Orders> searchAll();
	
	public int del(String ids);

	public List<Orders> searchByOrder(Orders orders);
}
