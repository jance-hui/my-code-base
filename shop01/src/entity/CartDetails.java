package entity;

public class CartDetails {

	private Integer id;
	private Cart cart;
	private Goods goods;
	private Integer goodsNUM;
	private float money;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getGoodsNUM() {
		return goodsNUM;
	}
	public void setGoodsNUM(Integer goodsNUM) {
		this.goodsNUM = goodsNUM;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	
}
