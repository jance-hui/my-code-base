package entity;

import java.util.List;

public class Goods {

	private Integer id; //id
	private String name; //商品名
	private float price; //价格
	private String details; //详情
	private Brand brand; //品牌
	private Genre genre; //类别
	private Integer stock; //库存
	private Integer salesVolume; //销量
	private List<GoodsImg> goodsImgs;
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public float getPrice() {
		return price;
	}
	public String getDetails() {
		return details;
	}
	public Brand getBrand() {
		return brand;
	}
	public Genre getGenre() {
		return genre;
	}
	public Integer getStock() {
		return stock;
	}
	public Integer getSalesVolume() {
		return salesVolume;
	}
	public List<GoodsImg> getGoodsImgs() {
		return goodsImgs;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	public void setGoodsImgs(List<GoodsImg> goodsImgs) {
		this.goodsImgs = goodsImgs;
	}
	
	
}
