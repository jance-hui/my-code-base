package service;

import java.util.List;

import entity.OrderDetails;

public interface OrderDetailsService {

	public boolean add(OrderDetails orderDetails,int id);

	public List<OrderDetails> searchByOID(Integer id);
}
