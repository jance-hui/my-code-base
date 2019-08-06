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
	public Orders searchByID(int id) {
		Orders orders = ordersDao.searchByID(id);
		return orders;
	}
	@Override
	public List<Orders> searchByUID(int id) {
		List<Orders> orderList = ordersDao.searchByUID(id);
		return orderList;
	}
	@Override
	public int searchCount() {
		int count = ordersDao.searchCount();
		return count;
	}
	@Override
	public List<Float> searchMoney() {
		List<Float> money = ordersDao.searchMoney();
		return money;
	}
	@Override
	public List<Orders> searchAll() {
		List<Orders> orders = ordersDao.searchAll();
		return orders;
	}
	@Override
	public boolean del(String ids) {
		int rs = ordersDao.del(ids);
		return rs>0;
	}
	@Override
	public List<Orders> searchByOrder(Orders orders) {
		List<Orders> oList = ordersDao.searchByOrder(orders);
		return oList;
	}

}
