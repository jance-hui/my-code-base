package service;

import java.util.List;

import entity.GoodsImg;

public interface GoodsImgService {

	public List<GoodsImg> search() ;

	public GoodsImg searchByID(int id) ;
	
	public List<GoodsImg> searchByGoods(int gID) ;

	public boolean add(Integer goodsId,GoodsImg gImg) ;
	
	public boolean del(String ids);
	
	public boolean update(GoodsImg gImg);

	public List<GoodsImg> searchByIDs(String goodsID) ;
	
}
