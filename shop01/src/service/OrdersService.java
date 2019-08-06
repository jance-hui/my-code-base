package service;

import java.util.List;

import entity.Orders;

public interface OrdersService {

	public int add(Orders orders);

	public Orders searchByID(int id);

	public List<Orders> searchByUID(int id);
}
