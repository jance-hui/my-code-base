package dao;

import java.util.List;

import entity.GoodsImg;

public interface GoodsImgDao {

	public List<GoodsImg> search() ;

	public GoodsImg searchByID(int id) ;
	
	public List<GoodsImg> searchByGoods(int gID) ;

	public int add(GoodsImg gImg) ;
	
	public int del(String ids);
	
	public int update(GoodsImg gImg);

	public List<GoodsImg> searchByIDs(String goodsID);
	
}
