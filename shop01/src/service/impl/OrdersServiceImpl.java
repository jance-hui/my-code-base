package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.OrdersDao;
import entity.Orders;
import service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	OrdersDao ordersDao;
	@Override
	public int add(Orders orders) {
		int rs = ordersDao.add(orders);
		if(rs>0) {
			rs = ordersDao.returnNewID();
		}
		return rs;
	}
	@Override
	public Orders searchByID(int id) {
		Orders orders = ordersDao.searchByID(id);
		return orders;
	}
	@Override
	public List<Orders> searchByUID(int id) {
		List<Orders> orderList = ordersDao.searchByUID(id);
		return orderList;
	}

}
