package service;

import java.util.List;

import entity.Orders;

public interface OrdersService {

	public Orders searchByID(int id);

	public List<Orders> searchByUID(int id);
	
	public int searchCount();

	public List<Float> searchMoney();

	public List<Orders> searchAll();

	public boolean del(String ids);

	public List<Orders> searchByOrder(Orders orders);
}
