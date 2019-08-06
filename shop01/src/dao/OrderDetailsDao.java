package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.OrderDetails;

public interface OrderDetailsDao {

	public int add(@Param(value="orderDetails")OrderDetails orderDetails,@Param(value="id") int id);

	public List<OrderDetails> searchByOID(Integer id);
}
