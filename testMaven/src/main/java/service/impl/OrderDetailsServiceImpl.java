package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.OrderDetailsDao;
import entity.OrderDetails;
import service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

	@Autowired
	OrderDetailsDao orderDetailsDao;
	@Override
	public boolean add(OrderDetails orderDetails,int id) {
		int rs = orderDetailsDao.add(orderDetails,id);
		return rs>0;
	}
	@Override
	public List<OrderDetails> searchByOID(Integer id) {
		List<OrderDetails> oDList = orderDetailsDao.searchByOID(id);
		return oDList;
	}

}
