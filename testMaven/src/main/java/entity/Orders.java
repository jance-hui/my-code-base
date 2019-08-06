package entity;

import java.util.List;

import position.Position;

public class Orders {

	private Integer id;
	private User user;
	private String orderNUM;
	private Position position;
	private float total;
	private String timeType;
	private List<OrderDetails> orderDetails;
	
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOrderNUM() {
		return orderNUM;
	}
	public void setOrderNUM(String orderNUM) {
		this.orderNUM = orderNUM;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
}
